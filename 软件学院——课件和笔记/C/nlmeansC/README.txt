% Non local means denoising


# ABOUT

* Author    : Antoni Buades <toni.buades@gmail.com>
* Copyright : (C) 2009, 2010 IPOL Image Processing On Line http://www.ipol.im/
* Licence

- nlmeans_ipol.cpp, libdenoising.cpp and libdenoising.h
may be linked to the EP patent 1,749,278 by A. Buades, T. Coll and J.M. Morel.
They are provided for scientific and education only.

- All the other files are distributed under the terms of the
  LGPLv3 license.


# OVERVIEW

This source code provides an implementation of the NL-means denoising algorithm as described in IPOL
    http://www.ipol.im/pub/algo/bcm_non_local_means_denoising/

This program reads and writes PNG images, but can be easily
adapted to any other file format.

Only 8bit RGB PNG images are handled. 


# REQUIREMENTS

The code is written in ANSI C and C++, and should compile on any
system with an ANSI C/C++ compiler.

The libpng header and libraries are required on the system for
compilation and execution. 

The implementation uses OPENMP which not supported by old versions of gcc (older than the 4.2). 


# COMPILATION

Simply use the provided makefile, with the command `make`.


# USAGE

usage: nlmeans_ipol image sigma noisy denoised 

`nlmeans_ipol ` takes 4 parameter: `nlmeans_ipol in.png sigma noisy.png denoised.png`
* `sigma`     : the noise standard deviation
* `in.png`   : initial noise free image
* `noisy.png`  : noisy image used by the denoising algorithm
* `denoised.png` : denoised image



# ABOUT THIS FILE

Copyright 2009, 2010 IPOL Image Processing On Line http://www.ipol.im/
Author: Antoni Buades <toni.buades@gmail.com>

Copying and distribution of this file, with or without modification,
are permitted in any medium without royalty provided the copyright
notice and this notice are preserved.  This file is offered as-is,
without any warranty.
