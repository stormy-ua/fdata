package com.fdata.scio

import com.fdata.core.FDataContext
import com.fdata.scio
import com.fdata.scio.syntax.ScioFCollectionSyntax
import com.spotify.scio.ScioContext
import com.spotify.scio.coders.{Coder => SCoder}

final class ScioFContext extends FDataContext {

  type Coder[T] = SCoder[T]
  type FCollection[T] = ScioFCollection[T]

  private val sc = ScioContext()

  val syntax = new ScioFCollectionSyntax

  def parallelize[T: SCoder](data: T*): ScioFCollection[T] =
    scio.ScioFCollection(sc.parallelize(data.toIterable))

  def run(): Unit = sc.run()
}
