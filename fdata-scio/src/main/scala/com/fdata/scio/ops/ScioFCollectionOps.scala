package com.fdata.scio.ops

import com.fdata.core.ops._
import com.fdata.scio.ScioFCollection
import com.spotify.scio.coders.{Coder => SCoder}

class ScioFCollectionOps[T](val coll: ScioFCollection[T])
  extends FCollectionOps[ScioFCollection, SCoder, T] {

  def flatMap[R: SCoder](fun: T => TraversableOnce[R]): ScioFCollection[R] =
    ScioFCollection(coll.internal.flatMap(fun))

  def map[R: SCoder](fun: T => R): ScioFCollection[R] =
    ScioFCollection(coll.internal.map(fun))
}

class ScioKeyedFCollectionOps[K, V](coll: ScioFCollection[(K, V)])
  extends KeyedFCollectionOps[ScioFCollection, SCoder, K, V] {

  def reduceByKey(op: (V, V) => V): ScioFCollection[(K, V)] =
    ScioFCollection(coll.internal.reduceByKey(op))

}

class ScioStringFCollectionOps(coll: ScioFCollection[String])
  extends StringFCollectionOps[ScioFCollection, SCoder] {

  def saveAsTextFile(path: String): Unit =
    coll.internal.saveAsTextFile(path)

}