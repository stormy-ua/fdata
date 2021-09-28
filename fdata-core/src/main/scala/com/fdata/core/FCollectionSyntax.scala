package com.fdata.core

trait FCollectionSyntax[FCollection[_], Coder[_]] {

  implicit def toFCollectionOps[T](coll: FCollection[T]): FCollectionOps[FCollection, Coder, T]

  implicit def toKeyedFCollectionOps[K, V](coll: FCollection[(K, V)])
  : KeyedFCollectionOps[FCollection, Coder, K, V]

  implicit def toStringFlinkFCollectionOps(coll: FCollection[String])
  : StringFCollectionOps[FCollection, Coder]
}
