package com.fdata.scio

import com.spotify.scio.values.SCollection

case class ScioFCollection[T](internal: SCollection[T])
