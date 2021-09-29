package com.fdata.mock.ops

import com.fdata.core.ops._
import com.fdata.mock.MockFContext
import com.fdata.mock.coders.MockCoder

class MockFCollectionOps[T](val coll: List[T])
  extends FCollectionOps[List, MockCoder, T] {

  def flatMap[R: MockCoder](fun: T => TraversableOnce[R]): List[R] = coll.flatMap(fun)

  def map[R: MockCoder](fun: T => R): List[R] = coll.map(fun)
}

class MockKeyedFCollectionOps[K, V](coll: List[(K, V)])
  extends KeyedFCollectionOps[List, MockCoder, K, V] {

  def reduceByKey(op: (V, V) => V): List[(K, V)] =
    coll.groupBy(_._1).map {
      case (k, g) => k -> g.map(_._2).reduce(op)
    }.toList


}

class MockStringFCollectionOps(ctx: MockFContext, coll: List[String])
  extends StringFCollectionOps[List, MockCoder] {

  def saveAsTextFile(path: String): Unit = ()
}
