
class Customer {

  String name
  boolean usesDieselCar, usesGreenCar, usesOldCar, usesSuperCar
  boolean usesPetrolCar = true 

  
}


class RiskDSL {
  
  Customer customer
  def scale = []
  def score = 0
  
  def static assess(customer, closure) {
    RiskDSL rdsl = new RiskDSL()
    rdsl.customer = new Customer()
    closure.delegate = rdsl
    closure()
  }
  
  def useScale(def scale) {
    this.scale = scale
  }
  
  def plus(def toAdd) {
    score+toAdd
  }
  
  def minus(def toSub) {
    score-toSub
  }
    
  def car(clo) { 
    clo()
  }
  
  def usesPetrolOrDiesel(clo) {
    if (customer.usesPetrolCar || customer.usesDieselCar)
      clo() 
  }

  def isGreen(clo) { 
    if (customer.usesGreenCar)
      clo()
  }
  
  def isMoreThan10years(clo) {
    if (customer.usesOldCar)
      clo()
  }

  //[...]
  //All evaluations skipped, follow examples above
  
  def compute(res) {
    score = score > scale.last() ? scale.last() : (score < scale.first() ? scale.first() : score)
  }  
}


Customer customer = new Customer()
customer.usesPetrolCar = true
RiskDSL.assess(customer) { 

   useScale 0..100

   car {
      usesPetrolOrDiesel { plus 10 }
      isGreen { minus 5 }
      isMoreThan10years { minus 10 }
   }

   // driver {
   //    neverHadClaims { minus 25 }      
   // }

   // insurance {
   //    ensureDamagesToTheVehicle { plus 20 }
   // }
   
   compute()

}
