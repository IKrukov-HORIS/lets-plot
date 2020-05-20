/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plotDemo.model.plotConfig

import jetbrains.datalore.base.json.JsonFormatter
import jetbrains.datalore.plot.parsePlotSpec
import jetbrains.datalore.plotDemo.model.AutoMpg
import jetbrains.datalore.plotDemo.model.PlotConfigDemoBase

@Suppress("DuplicatedCode")

class CorrelationStat : PlotConfigDemoBase() {

//    private val jsonMpg : String
//
//    init {
//        val mpg = AutoMpg.df
//        jsonMpg   = JsonFormatter().formatJson(mpg)
//    }


    fun plotSpecList(): List<Map<String, Any>> {
        return listOf(
            aesDemo(),
            noaesDemo()
        )
    }

    /*
    private fun aesDemo(): Map<String, Any> {
        val spec = """
{
  "data": 
""".trimMargin() + jsonMpg +
""",
  "mapping": {
    "x": null,
    "y": null
  },
  "data_meta": {},
  "kind": "plot",
  "scales": [],
  "layers": [
    {
      "geom": "point",
      "stat": "corr",
      "data": null,
      "mapping": {
        "x": "..var1..",
        "y": "..var2.."
      },
      "position": null,
      "show_legend": null,
      "data_meta": {},
      "sampling": null,
      "animation": null,
      "map_join": null
    }
  ]
}
""".trimMargin()

        return parsePlotSpec(spec)
    }
    */


    private fun aesDemo(): Map<String, Any> {
        val spec = """
{
  "data": {
    "Unnamed: 0": [
      1,
      2,
      3,
      4,
      5,
      6,
      7,
      8,
      9,
      10,
      11,
      12,
      13,
      14,
      15,
      16,
      17,
      18,
      19,
      20,
      21,
      22,
      23,
      24,
      25,
      26,
      27,
      28,
      29,
      30,
      31,
      32,
      33,
      34,
      35,
      36,
      37,
      38,
      39,
      40,
      41,
      42,
      43,
      44,
      45,
      46,
      47,
      48,
      49,
      50,
      51,
      52,
      53,
      54,
      55,
      56,
      57,
      58,
      59,
      60,
      61,
      62,
      63,
      64,
      65,
      66,
      67,
      68,
      69,
      70,
      71,
      72,
      73,
      74,
      75,
      76,
      77,
      78,
      79,
      80,
      81,
      82,
      83,
      84,
      85,
      86,
      87,
      88,
      89,
      90,
      91,
      92,
      93,
      94,
      95,
      96,
      97,
      98,
      99,
      100,
      101,
      102,
      103,
      104,
      105,
      106,
      107,
      108,
      109,
      110,
      111,
      112,
      113,
      114,
      115,
      116,
      117,
      118,
      119,
      120,
      121,
      122,
      123,
      124,
      125,
      126,
      127,
      128,
      129,
      130,
      131,
      132,
      133,
      134,
      135,
      136,
      137,
      138,
      139,
      140,
      141,
      142,
      143,
      144,
      145,
      146,
      147,
      148,
      149,
      150,
      151,
      152,
      153,
      154,
      155,
      156,
      157,
      158,
      159,
      160,
      161,
      162,
      163,
      164,
      165,
      166,
      167,
      168,
      169,
      170,
      171,
      172,
      173,
      174,
      175,
      176,
      177,
      178,
      179,
      180,
      181,
      182,
      183,
      184,
      185,
      186,
      187,
      188,
      189,
      190,
      191,
      192,
      193,
      194,
      195,
      196,
      197,
      198,
      199,
      200,
      201,
      202,
      203,
      204,
      205,
      206,
      207,
      208,
      209,
      210,
      211,
      212,
      213,
      214,
      215,
      216,
      217,
      218,
      219,
      220,
      221,
      222,
      223,
      224,
      225,
      226,
      227,
      228,
      229,
      230,
      231,
      232,
      233,
      234
    ],
    "displ": [
      1.8,
      1.8,
      2.0,
      2.0,
      2.8,
      2.8,
      3.1,
      1.8,
      1.8,
      2.0,
      2.0,
      2.8,
      2.8,
      3.1,
      3.1,
      2.8,
      3.1,
      4.2,
      5.3,
      5.3,
      5.3,
      5.7,
      6.0,
      5.7,
      5.7,
      6.2,
      6.2,
      7.0,
      5.3,
      5.3,
      5.7,
      6.5,
      2.4,
      2.4,
      3.1,
      3.5,
      3.6,
      2.4,
      3.0,
      3.3,
      3.3,
      3.3,
      3.3,
      3.3,
      3.8,
      3.8,
      3.8,
      4.0,
      3.7,
      3.7,
      3.9,
      3.9,
      4.7,
      4.7,
      4.7,
      5.2,
      5.2,
      3.9,
      4.7,
      4.7,
      4.7,
      5.2,
      5.7,
      5.9,
      4.7,
      4.7,
      4.7,
      4.7,
      4.7,
      4.7,
      5.2,
      5.2,
      5.7,
      5.9,
      4.6,
      5.4,
      5.4,
      4.0,
      4.0,
      4.0,
      4.0,
      4.6,
      5.0,
      4.2,
      4.2,
      4.6,
      4.6,
      4.6,
      5.4,
      5.4,
      3.8,
      3.8,
      4.0,
      4.0,
      4.6,
      4.6,
      4.6,
      4.6,
      5.4,
      1.6,
      1.6,
      1.6,
      1.6,
      1.6,
      1.8,
      1.8,
      1.8,
      2.0,
      2.4,
      2.4,
      2.4,
      2.4,
      2.5,
      2.5,
      3.3,
      2.0,
      2.0,
      2.0,
      2.0,
      2.7,
      2.7,
      2.7,
      3.0,
      3.7,
      4.0,
      4.7,
      4.7,
      4.7,
      5.7,
      6.1,
      4.0,
      4.2,
      4.4,
      4.6,
      5.4,
      5.4,
      5.4,
      4.0,
      4.0,
      4.6,
      5.0,
      2.4,
      2.4,
      2.5,
      2.5,
      3.5,
      3.5,
      3.0,
      3.0,
      3.5,
      3.3,
      3.3,
      4.0,
      5.6,
      3.1,
      3.8,
      3.8,
      3.8,
      5.3,
      2.5,
      2.5,
      2.5,
      2.5,
      2.5,
      2.5,
      2.2,
      2.2,
      2.5,
      2.5,
      2.5,
      2.5,
      2.5,
      2.5,
      2.7,
      2.7,
      3.4,
      3.4,
      4.0,
      4.7,
      2.2,
      2.2,
      2.4,
      2.4,
      3.0,
      3.0,
      3.5,
      2.2,
      2.2,
      2.4,
      2.4,
      3.0,
      3.0,
      3.3,
      1.8,
      1.8,
      1.8,
      1.8,
      1.8,
      4.7,
      5.7,
      2.7,
      2.7,
      2.7,
      3.4,
      3.4,
      4.0,
      4.0,
      2.0,
      2.0,
      2.0,
      2.0,
      2.8,
      1.9,
      2.0,
      2.0,
      2.0,
      2.0,
      2.5,
      2.5,
      2.8,
      2.8,
      1.9,
      1.9,
      2.0,
      2.0,
      2.5,
      2.5,
      1.8,
      1.8,
      2.0,
      2.0,
      2.8,
      2.8,
      3.6
    ],
    "cyl": [
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      6,
      6,
      6,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      4,
      4,
      6,
      6,
      6,
      4,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      8,
      8,
      8,
      8,
      8,
      6,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      6,
      6,
      6,
      6,
      8,
      8,
      6,
      6,
      8,
      8,
      8,
      8,
      8,
      6,
      6,
      6,
      6,
      8,
      8,
      8,
      8,
      8,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      6,
      6,
      6,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      6,
      6,
      8,
      8,
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      8,
      6,
      6,
      6,
      6,
      8,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      8,
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      4,
      4,
      4,
      4,
      4,
      8,
      8,
      4,
      4,
      4,
      6,
      6,
      6,
      6,
      4,
      4,
      4,
      4,
      6,
      4,
      4,
      4,
      4,
      4,
      5,
      5,
      6,
      6,
      4,
      4,
      4,
      4,
      5,
      5,
      4,
      4,
      4,
      4,
      6,
      6,
      6
    ],
    "cty": [
      18,
      21,
      20,
      21,
      16,
      18,
      18,
      18,
      16,
      20,
      19,
      15,
      17,
      17,
      15,
      15,
      17,
      16,
      14,
      11,
      14,
      13,
      12,
      16,
      15,
      16,
      15,
      15,
      14,
      11,
      11,
      14,
      19,
      22,
      18,
      18,
      17,
      18,
      17,
      16,
      16,
      17,
      17,
      11,
      15,
      15,
      16,
      16,
      15,
      14,
      13,
      14,
      14,
      14,
      9,
      11,
      11,
      13,
      13,
      9,
      13,
      11,
      13,
      11,
      12,
      9,
      13,
      13,
      12,
      9,
      11,
      11,
      13,
      11,
      11,
      11,
      12,
      14,
      15,
      14,
      13,
      13,
      13,
      14,
      14,
      13,
      13,
      13,
      11,
      13,
      18,
      18,
      17,
      16,
      15,
      15,
      15,
      15,
      14,
      28,
      24,
      25,
      23,
      24,
      26,
      25,
      24,
      21,
      18,
      18,
      21,
      21,
      18,
      18,
      19,
      19,
      19,
      20,
      20,
      17,
      16,
      17,
      17,
      15,
      15,
      14,
      9,
      14,
      13,
      11,
      11,
      12,
      12,
      11,
      11,
      11,
      12,
      14,
      13,
      13,
      13,
      21,
      19,
      23,
      23,
      19,
      19,
      18,
      19,
      19,
      14,
      15,
      14,
      12,
      18,
      16,
      17,
      18,
      16,
      18,
      18,
      20,
      19,
      20,
      18,
      21,
      19,
      19,
      19,
      20,
      20,
      19,
      20,
      15,
      16,
      15,
      15,
      16,
      14,
      21,
      21,
      21,
      21,
      18,
      18,
      19,
      21,
      21,
      21,
      22,
      18,
      18,
      18,
      24,
      24,
      26,
      28,
      26,
      11,
      13,
      15,
      16,
      17,
      15,
      15,
      15,
      16,
      21,
      19,
      21,
      22,
      17,
      33,
      21,
      19,
      22,
      21,
      21,
      21,
      16,
      17,
      35,
      29,
      21,
      19,
      20,
      20,
      21,
      18,
      19,
      21,
      16,
      18,
      17
    ],
    "hwy": [
      29,
      29,
      31,
      30,
      26,
      26,
      27,
      26,
      25,
      28,
      27,
      25,
      25,
      25,
      25,
      24,
      25,
      23,
      20,
      15,
      20,
      17,
      17,
      26,
      23,
      26,
      25,
      24,
      19,
      14,
      15,
      17,
      27,
      30,
      26,
      29,
      26,
      24,
      24,
      22,
      22,
      24,
      24,
      17,
      22,
      21,
      23,
      23,
      19,
      18,
      17,
      17,
      19,
      19,
      12,
      17,
      15,
      17,
      17,
      12,
      17,
      16,
      18,
      15,
      16,
      12,
      17,
      17,
      16,
      12,
      15,
      16,
      17,
      15,
      17,
      17,
      18,
      17,
      19,
      17,
      19,
      19,
      17,
      17,
      17,
      16,
      16,
      17,
      15,
      17,
      26,
      25,
      26,
      24,
      21,
      22,
      23,
      22,
      20,
      33,
      32,
      32,
      29,
      32,
      34,
      36,
      36,
      29,
      26,
      27,
      30,
      31,
      26,
      26,
      28,
      26,
      29,
      28,
      27,
      24,
      24,
      24,
      22,
      19,
      20,
      17,
      12,
      19,
      18,
      14,
      15,
      18,
      18,
      15,
      17,
      16,
      18,
      17,
      19,
      19,
      17,
      29,
      27,
      31,
      32,
      27,
      26,
      26,
      25,
      25,
      17,
      17,
      20,
      18,
      26,
      26,
      27,
      28,
      25,
      25,
      24,
      27,
      25,
      26,
      23,
      26,
      26,
      26,
      26,
      25,
      27,
      25,
      27,
      20,
      20,
      19,
      17,
      20,
      17,
      29,
      27,
      31,
      31,
      26,
      26,
      28,
      27,
      29,
      31,
      31,
      26,
      26,
      27,
      30,
      33,
      35,
      37,
      35,
      15,
      18,
      20,
      20,
      22,
      17,
      19,
      18,
      20,
      29,
      26,
      29,
      29,
      24,
      44,
      29,
      26,
      29,
      29,
      29,
      29,
      23,
      24,
      44,
      41,
      29,
      26,
      28,
      29,
      29,
      29,
      28,
      29,
      26,
      26,
      26
    ]
  },
  "mapping": {
    "x": null,
    "y": null
  },
  "data_meta": {},
  "kind": "plot",
  "scales": [],
  "layers": [
    {
      "geom": "point",
      "stat": "corr",
      "data": null,
      "mapping": {
        "x": "..x..",
        "y": null
      },
      "position": null,
      "show_legend": null,
      "data_meta": {},
      "sampling": null,
      "animation": null,
      "map_join": null
    }
  ]
}
""".trimMargin()

        return parsePlotSpec(spec)
    }

    private fun noaesDemo(): Map<String, Any> {
        val spec = """
{
  "data": {
    "Unnamed: 0": [
      1,
      2,
      3,
      4,
      5,
      6,
      7,
      8,
      9,
      10,
      11,
      12,
      13,
      14,
      15,
      16,
      17,
      18,
      19,
      20,
      21,
      22,
      23,
      24,
      25,
      26,
      27,
      28,
      29,
      30,
      31,
      32,
      33,
      34,
      35,
      36,
      37,
      38,
      39,
      40,
      41,
      42,
      43,
      44,
      45,
      46,
      47,
      48,
      49,
      50,
      51,
      52,
      53,
      54,
      55,
      56,
      57,
      58,
      59,
      60,
      61,
      62,
      63,
      64,
      65,
      66,
      67,
      68,
      69,
      70,
      71,
      72,
      73,
      74,
      75,
      76,
      77,
      78,
      79,
      80,
      81,
      82,
      83,
      84,
      85,
      86,
      87,
      88,
      89,
      90,
      91,
      92,
      93,
      94,
      95,
      96,
      97,
      98,
      99,
      100,
      101,
      102,
      103,
      104,
      105,
      106,
      107,
      108,
      109,
      110,
      111,
      112,
      113,
      114,
      115,
      116,
      117,
      118,
      119,
      120,
      121,
      122,
      123,
      124,
      125,
      126,
      127,
      128,
      129,
      130,
      131,
      132,
      133,
      134,
      135,
      136,
      137,
      138,
      139,
      140,
      141,
      142,
      143,
      144,
      145,
      146,
      147,
      148,
      149,
      150,
      151,
      152,
      153,
      154,
      155,
      156,
      157,
      158,
      159,
      160,
      161,
      162,
      163,
      164,
      165,
      166,
      167,
      168,
      169,
      170,
      171,
      172,
      173,
      174,
      175,
      176,
      177,
      178,
      179,
      180,
      181,
      182,
      183,
      184,
      185,
      186,
      187,
      188,
      189,
      190,
      191,
      192,
      193,
      194,
      195,
      196,
      197,
      198,
      199,
      200,
      201,
      202,
      203,
      204,
      205,
      206,
      207,
      208,
      209,
      210,
      211,
      212,
      213,
      214,
      215,
      216,
      217,
      218,
      219,
      220,
      221,
      222,
      223,
      224,
      225,
      226,
      227,
      228,
      229,
      230,
      231,
      232,
      233,
      234
    ],
    "displ": [
      1.8,
      1.8,
      2.0,
      2.0,
      2.8,
      2.8,
      3.1,
      1.8,
      1.8,
      2.0,
      2.0,
      2.8,
      2.8,
      3.1,
      3.1,
      2.8,
      3.1,
      4.2,
      5.3,
      5.3,
      5.3,
      5.7,
      6.0,
      5.7,
      5.7,
      6.2,
      6.2,
      7.0,
      5.3,
      5.3,
      5.7,
      6.5,
      2.4,
      2.4,
      3.1,
      3.5,
      3.6,
      2.4,
      3.0,
      3.3,
      3.3,
      3.3,
      3.3,
      3.3,
      3.8,
      3.8,
      3.8,
      4.0,
      3.7,
      3.7,
      3.9,
      3.9,
      4.7,
      4.7,
      4.7,
      5.2,
      5.2,
      3.9,
      4.7,
      4.7,
      4.7,
      5.2,
      5.7,
      5.9,
      4.7,
      4.7,
      4.7,
      4.7,
      4.7,
      4.7,
      5.2,
      5.2,
      5.7,
      5.9,
      4.6,
      5.4,
      5.4,
      4.0,
      4.0,
      4.0,
      4.0,
      4.6,
      5.0,
      4.2,
      4.2,
      4.6,
      4.6,
      4.6,
      5.4,
      5.4,
      3.8,
      3.8,
      4.0,
      4.0,
      4.6,
      4.6,
      4.6,
      4.6,
      5.4,
      1.6,
      1.6,
      1.6,
      1.6,
      1.6,
      1.8,
      1.8,
      1.8,
      2.0,
      2.4,
      2.4,
      2.4,
      2.4,
      2.5,
      2.5,
      3.3,
      2.0,
      2.0,
      2.0,
      2.0,
      2.7,
      2.7,
      2.7,
      3.0,
      3.7,
      4.0,
      4.7,
      4.7,
      4.7,
      5.7,
      6.1,
      4.0,
      4.2,
      4.4,
      4.6,
      5.4,
      5.4,
      5.4,
      4.0,
      4.0,
      4.6,
      5.0,
      2.4,
      2.4,
      2.5,
      2.5,
      3.5,
      3.5,
      3.0,
      3.0,
      3.5,
      3.3,
      3.3,
      4.0,
      5.6,
      3.1,
      3.8,
      3.8,
      3.8,
      5.3,
      2.5,
      2.5,
      2.5,
      2.5,
      2.5,
      2.5,
      2.2,
      2.2,
      2.5,
      2.5,
      2.5,
      2.5,
      2.5,
      2.5,
      2.7,
      2.7,
      3.4,
      3.4,
      4.0,
      4.7,
      2.2,
      2.2,
      2.4,
      2.4,
      3.0,
      3.0,
      3.5,
      2.2,
      2.2,
      2.4,
      2.4,
      3.0,
      3.0,
      3.3,
      1.8,
      1.8,
      1.8,
      1.8,
      1.8,
      4.7,
      5.7,
      2.7,
      2.7,
      2.7,
      3.4,
      3.4,
      4.0,
      4.0,
      2.0,
      2.0,
      2.0,
      2.0,
      2.8,
      1.9,
      2.0,
      2.0,
      2.0,
      2.0,
      2.5,
      2.5,
      2.8,
      2.8,
      1.9,
      1.9,
      2.0,
      2.0,
      2.5,
      2.5,
      1.8,
      1.8,
      2.0,
      2.0,
      2.8,
      2.8,
      3.6
    ],
    "cyl": [
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      6,
      6,
      6,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      4,
      4,
      6,
      6,
      6,
      4,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      8,
      8,
      8,
      8,
      8,
      6,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      6,
      6,
      6,
      6,
      8,
      8,
      6,
      6,
      8,
      8,
      8,
      8,
      8,
      6,
      6,
      6,
      6,
      8,
      8,
      8,
      8,
      8,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      6,
      6,
      6,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      8,
      6,
      6,
      8,
      8,
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      6,
      8,
      6,
      6,
      6,
      6,
      8,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      8,
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      4,
      4,
      4,
      4,
      6,
      6,
      6,
      4,
      4,
      4,
      4,
      4,
      8,
      8,
      4,
      4,
      4,
      6,
      6,
      6,
      6,
      4,
      4,
      4,
      4,
      6,
      4,
      4,
      4,
      4,
      4,
      5,
      5,
      6,
      6,
      4,
      4,
      4,
      4,
      5,
      5,
      4,
      4,
      4,
      4,
      6,
      6,
      6
    ],
    "cty": [
      18,
      21,
      20,
      21,
      16,
      18,
      18,
      18,
      16,
      20,
      19,
      15,
      17,
      17,
      15,
      15,
      17,
      16,
      14,
      11,
      14,
      13,
      12,
      16,
      15,
      16,
      15,
      15,
      14,
      11,
      11,
      14,
      19,
      22,
      18,
      18,
      17,
      18,
      17,
      16,
      16,
      17,
      17,
      11,
      15,
      15,
      16,
      16,
      15,
      14,
      13,
      14,
      14,
      14,
      9,
      11,
      11,
      13,
      13,
      9,
      13,
      11,
      13,
      11,
      12,
      9,
      13,
      13,
      12,
      9,
      11,
      11,
      13,
      11,
      11,
      11,
      12,
      14,
      15,
      14,
      13,
      13,
      13,
      14,
      14,
      13,
      13,
      13,
      11,
      13,
      18,
      18,
      17,
      16,
      15,
      15,
      15,
      15,
      14,
      28,
      24,
      25,
      23,
      24,
      26,
      25,
      24,
      21,
      18,
      18,
      21,
      21,
      18,
      18,
      19,
      19,
      19,
      20,
      20,
      17,
      16,
      17,
      17,
      15,
      15,
      14,
      9,
      14,
      13,
      11,
      11,
      12,
      12,
      11,
      11,
      11,
      12,
      14,
      13,
      13,
      13,
      21,
      19,
      23,
      23,
      19,
      19,
      18,
      19,
      19,
      14,
      15,
      14,
      12,
      18,
      16,
      17,
      18,
      16,
      18,
      18,
      20,
      19,
      20,
      18,
      21,
      19,
      19,
      19,
      20,
      20,
      19,
      20,
      15,
      16,
      15,
      15,
      16,
      14,
      21,
      21,
      21,
      21,
      18,
      18,
      19,
      21,
      21,
      21,
      22,
      18,
      18,
      18,
      24,
      24,
      26,
      28,
      26,
      11,
      13,
      15,
      16,
      17,
      15,
      15,
      15,
      16,
      21,
      19,
      21,
      22,
      17,
      33,
      21,
      19,
      22,
      21,
      21,
      21,
      16,
      17,
      35,
      29,
      21,
      19,
      20,
      20,
      21,
      18,
      19,
      21,
      16,
      18,
      17
    ],
    "hwy": [
      29,
      29,
      31,
      30,
      26,
      26,
      27,
      26,
      25,
      28,
      27,
      25,
      25,
      25,
      25,
      24,
      25,
      23,
      20,
      15,
      20,
      17,
      17,
      26,
      23,
      26,
      25,
      24,
      19,
      14,
      15,
      17,
      27,
      30,
      26,
      29,
      26,
      24,
      24,
      22,
      22,
      24,
      24,
      17,
      22,
      21,
      23,
      23,
      19,
      18,
      17,
      17,
      19,
      19,
      12,
      17,
      15,
      17,
      17,
      12,
      17,
      16,
      18,
      15,
      16,
      12,
      17,
      17,
      16,
      12,
      15,
      16,
      17,
      15,
      17,
      17,
      18,
      17,
      19,
      17,
      19,
      19,
      17,
      17,
      17,
      16,
      16,
      17,
      15,
      17,
      26,
      25,
      26,
      24,
      21,
      22,
      23,
      22,
      20,
      33,
      32,
      32,
      29,
      32,
      34,
      36,
      36,
      29,
      26,
      27,
      30,
      31,
      26,
      26,
      28,
      26,
      29,
      28,
      27,
      24,
      24,
      24,
      22,
      19,
      20,
      17,
      12,
      19,
      18,
      14,
      15,
      18,
      18,
      15,
      17,
      16,
      18,
      17,
      19,
      19,
      17,
      29,
      27,
      31,
      32,
      27,
      26,
      26,
      25,
      25,
      17,
      17,
      20,
      18,
      26,
      26,
      27,
      28,
      25,
      25,
      24,
      27,
      25,
      26,
      23,
      26,
      26,
      26,
      26,
      25,
      27,
      25,
      27,
      20,
      20,
      19,
      17,
      20,
      17,
      29,
      27,
      31,
      31,
      26,
      26,
      28,
      27,
      29,
      31,
      31,
      26,
      26,
      27,
      30,
      33,
      35,
      37,
      35,
      15,
      18,
      20,
      20,
      22,
      17,
      19,
      18,
      20,
      29,
      26,
      29,
      29,
      24,
      44,
      29,
      26,
      29,
      29,
      29,
      29,
      23,
      24,
      44,
      41,
      29,
      26,
      28,
      29,
      29,
      29,
      28,
      29,
      26,
      26,
      26
    ]
  },
  "mapping": {
    "x": null,
    "y": null
  },
  "data_meta": {},
  "kind": "plot",
  "scales": [],
  "layers": [
    {
      "geom": "point",
      "stat": "corr",
      "data": null,
      "mapping": {
        "x": null,
        "y": null
      },
      "position": null,
      "show_legend": null,
      "data_meta": {},
      "sampling": null,
      "animation": null,
      "map_join": null
    }
  ]
}
""".trimMargin()

        return parsePlotSpec(spec)
    }
}