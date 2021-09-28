package com.fdata.core.ops

trait StringFCollectionOps[FCollection[_], Coder[_]] {

  def saveAsTextFile(path: String): Unit

}
