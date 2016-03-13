package info.collaboration_station
import info.collaboration_station.debug.ImplicitTrace

/**
  * Created by johnreed on 3/12/16.
  */
package object debug {
  import scala.language.implicitConversions // Warning: implicit conversions language feature

  /**
    * Converter method that puts the value "me" inside ImplicitTrace for additional functionality
    *
    * @param me the value to be put inside the wrapper
    * @tparam MyType the type of the value to be put inside the wrapper
    * @return A new ImplicitTrace wrapper which appends print trace related functionality to the value.
    */
  implicit def toTraceable[MyType](me: MyType) = new ImplicitTrace[MyType](me)


}
