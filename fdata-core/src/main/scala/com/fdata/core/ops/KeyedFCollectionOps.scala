package com.fdata.core.ops

trait KeyedFCollectionOps[FCollection[_], Coder[_], K, V] {

  def reduceByKey(op: (V, V) => V): FCollection[(K, V)]

}
