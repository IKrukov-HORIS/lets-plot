#  Copyright (c) 2020. JetBrains s.r.o.
#  Use of this source code is governed by the MIT license that can be found in the LICENSE file.


from .geom import geom_point
from .scale import scale_color_continuous
from .scale import scale_size
from .theme_ import theme
from .theme_ import element_blank
from .coord import coord_fixed

__all__ = ['geom_corr_point']


def geom_corr_point(mapping=None, data=None, position=None, show_legend=None, animation=None, map_join=None,
                    **other_args):
    return geom_point(mapping=mapping, data=data, stat='corr', position=position, map_join=map_join,
                      show_legend=show_legend,
                      animation=animation,
                      **other_args) + \
           scale_color_continuous(name='Correlation', low='dark_blue', high='red') + scale_size(name='') + \
           theme(axis_title=element_blank()) + coord_fixed()
