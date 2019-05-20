package jetbrains.datalore.visualization.plot.gog.config

import jetbrains.datalore.base.gcommon.base.Preconditions.checkArgument
import jetbrains.datalore.visualization.plot.base.data.Stat
import jetbrains.datalore.visualization.plot.base.data.stat.*
import jetbrains.datalore.visualization.plot.base.data.stat.BoxplotStat.Companion.P_COEF
import jetbrains.datalore.visualization.plot.base.data.stat.BoxplotStat.Companion.P_VARWIDTH

open class StatProto {

    internal fun defaultOptions(statName: String): Map<String, Any> {
        checkArgument(DEFAULTS.containsKey(statName), "Unknown stat name: '$statName'")
        return DEFAULTS[statName]!!
    }

    internal fun createStat(statKind: StatKind, options: Map<*, *>): Stat {
        // ToDo: pass 'option accessor'
        // ToDo: get rid of 'if'-s: everything must be present in the 'defaults'
        when (statKind) {
            StatKind.IDENTITY -> return Stats.IDENTITY
            StatKind.COUNT -> return Stats.count()
            StatKind.BIN -> {
                val binStat = Stats.bin()
                if (options.containsKey("bins")) {
                    binStat.binCount((options["bins"] as Number).toInt())
                }
                if (options.containsKey("binwidth")) {
                    binStat.binWidth((options["binwidth"] as Number).toDouble())
                }
                if (options.containsKey("center")) {
                    binStat.center((options["center"] as Number).toDouble())
                }
                if (options.containsKey("boundary")) {
                    binStat.boundary((options["boundary"] as Number).toDouble())
                }
                return binStat.build()
            }

            StatKind.CONTOUR -> {
                val contourStat = Stats.contour()
                if (options.containsKey("bins")) {
                    contourStat.binCount((options["bins"] as Number).toInt())
                }
                if (options.containsKey("binwidth")) {
                    contourStat.binWidth((options["binwidth"] as Number).toDouble())
                }
                return contourStat.build()
            }

            StatKind.CONTOURF -> {
                val contourfStat = Stats.contourf()
                if (options.containsKey("bins")) {
                    contourfStat.binCount((options["bins"] as Number).toInt())
                }
                if (options.containsKey("binwidth")) {
                    contourfStat.binWidth((options["binwidth"] as Number).toDouble())
                }
                return contourfStat.build()
            }

            StatKind.SMOOTH -> return configureSmoothStat(options)

            StatKind.BOXPLOT -> {
                val boxplotStat = Stats.boxplot()
                val opts = OptionsAccessor.over(options)
                boxplotStat.setComputeWidth(opts.getBoolean(P_VARWIDTH))
                boxplotStat.setWhiskerIQRRatio(opts.getDouble(P_COEF)!!)
                return boxplotStat
            }

            StatKind.DENSITY -> {
                val densityStat = Stats.density()
                if (options.containsKey("kernel")) {
                    val method = options["kernel"] as String
                    densityStat.setKernel(DensityStatUtil.toKernel(method))
                }
                if (options.containsKey("bw")) {
                    val bw = options["bw"]
                    if (bw is Number) {
                        densityStat.setBandWidth(bw.toDouble())
                    } else {
                        densityStat.setBandWidthMethod(DensityStatUtil.toBandWidthMethod(bw as String))
                    }
                }
                if (options.containsKey("n")) {
                    densityStat.setN((options["n"] as Number).toInt())
                }
                if (options.containsKey("adjust")) {
                    densityStat.setAdjust((options["adjust"] as Number).toDouble())
                }
                return densityStat
            }

            StatKind.DENSITY2D -> {
                val density2dStat = createDensity2dStat()
                return configureDensity2dStat(density2dStat, options)
            }

            StatKind.DENSITY2DF -> {
                val density2dfStat = createDensity2dfStat()
                return configureDensity2dStat(density2dfStat, options)
            }

            else -> throw IllegalArgumentException("Unknown stat: '$statKind'")
        }
    }

    private fun configureSmoothStat(options: Map<*, *>): Stat {
        // Params:
        //  method - smoothing method: lm, glm, gam, loess, rlm
        //  n (80) - number of points to evaluate smoother at
        //  se (TRUE ) - display confidence interval around smooth?
        //  level (0.95) - level of confidence interval to use
        val stat = createSmoothStat()

        if (options.containsKey("n")) {
            stat.smootherPointCount = (options["n"] as Number).toInt()
        }

        if (options.containsKey("method")) {
            val method = options["method"] as String
            val smoothMethod: SmoothStatShell.Method
            when (method) {
                "lm" -> smoothMethod = SmoothStatShell.Method.LM
                "loess", "lowess" -> smoothMethod = SmoothStatShell.Method.LOESS
                "glm" -> smoothMethod = SmoothStatShell.Method.GLM
                "gam" -> smoothMethod = SmoothStatShell.Method.GAM
                "rlm" -> smoothMethod = SmoothStatShell.Method.RLM
                else -> throw IllegalArgumentException("Unsupported smoother method: $method")
            }
            stat.smoothingMethod = smoothMethod
        }

        if (options.containsKey("level")) {
            stat.confidenceLevel = (options["level"] as Number).toDouble()
        }

        if (options.containsKey("se")) {
            val se = options["se"]
            if (se is Boolean) {
                stat.isDisplayConfidenceInterval = se
            }
        }

        return stat
    }

    private fun configureDensity2dStat(stat: Density2dStatShell, options: Map<*, *>): Stat {
        if (options.containsKey("kernel")) {
            val method = options["kernel"] as String
            stat.setKernel(DensityStatUtil.toKernel(method))
        }
        if (options.containsKey("bw")) {
            val bw = options["bw"]
            if (bw is List<*>) {
                for (i in bw.indices) {
                    val v = bw[i]
                    if (i == 0) {
                        stat.setBandWidthX((v as Number).toDouble())
                    } else {
                        stat.setBandWidthY((v as Number).toDouble())
                        break
                    }
                }
            } else if (bw is Number) {
                stat.setBandWidthX(bw.toDouble())
                stat.setBandWidthY(bw.toDouble())
            } else if (bw is String) {
                stat.bandWidthMethod = DensityStatUtil.toBandWidthMethod(bw)
            }
        }
        if (options.containsKey("n")) {
            val n = options["n"]
            if (n is List<*>) {
                for (i in n.indices) {
                    val v = n[i]
                    if (i == 0) {
                        stat.nx = (v as Number).toInt()
                    } else {
                        stat.ny = (v as Number).toInt()
                        break
                    }
                }
            } else if (n is Number) {
                stat.nx = n.toInt()
                stat.ny = n.toInt()
            }
        }
        if (options.containsKey("adjust")) {
            stat.adjust = (options["adjust"] as Number).toDouble()
        }
        if (options.containsKey("contour")) {
            stat.isContour = options["contour"] as Boolean
        }
        if (options.containsKey("bins")) {
            stat.setBinCount((options["bins"] as Number).toInt())
        }
        if (options.containsKey("binwidth")) {
            stat.setBinWidth((options["binwidth"] as Number).toDouble())
        }
        return stat
    }

    protected open fun createSmoothStat(): SmoothStatShell {
        return Stats.smooth()
    }

    protected open fun createDensity2dStat(): Density2dStatShell {
        return Stats.density2d()
    }

    protected open fun createDensity2dfStat(): Density2dStatShell {
        return Stats.density2df()
    }

    companion object {
        private val DEFAULTS = HashMap<String, Map<String, Any>>()

        // ToDo: add default geom
        init {
            DEFAULTS["identity"] = emptyMap()
            DEFAULTS["count"] = emptyMap()
            DEFAULTS["bin"] = createBinDefaults()
            DEFAULTS["smooth"] = emptyMap()
            DEFAULTS["contour"] = createContourDefaults()
            DEFAULTS["contourf"] = createContourfDefaults()
            DEFAULTS["boxplot"] = createBoxplotDefaults()
            DEFAULTS["density"] = createDensityDefaults()
            DEFAULTS["density2d"] = createDensity2dDefaults()
            DEFAULTS["density2df"] = createDensity2dDefaults()
        }

        private fun createBinDefaults(): Map<String, Any> {
            return mapOf(
                    "bins" to BinStatBuilder.DEF_BIN_COUNT
            )
        }

        private fun createContourDefaults(): Map<String, Any> {
            return mapOf(
                    "bins" to ContourStatBuilder.DEF_BIN_COUNT
            )
        }

        private fun createContourfDefaults(): Map<String, Any> {
            return mapOf(
                    "bins" to ContourfStatBuilder.DEF_BIN_COUNT
            )
        }

        private fun createBoxplotDefaults(): Map<String, Any> {
            return mapOf(
                    P_COEF to BoxplotStat.DEF_WHISKER_IQR_RATIO,
                    P_VARWIDTH to BoxplotStat.DEF_COMPUTE_WIDTH
            )
        }

        private fun createDensityDefaults(): Map<String, Any> {
            return mapOf(
                    "n" to DensityStat.DEF_N,
                    "kernel" to DensityStat.DEF_KERNEL,
                    "bw" to DensityStat.DEF_BW,
                    "adjust" to DensityStat.DEF_ADJUST
            )
        }

        private fun createDensity2dDefaults(): Map<String, Any> {
            return mapOf(
                    "n" to Density2dStatShell.DEF_N,
                    "kernel" to Density2dStatShell.DEF_KERNEL,
                    "bw" to Density2dStatShell.DEF_BW,
                    "adjust" to Density2dStatShell.DEF_ADJUST,
                    "contour" to Density2dStatShell.DEF_CONTOUR,
                    "bins" to Density2dStatShell.DEF_BIN_COUNT
            )
        }
    }
}
