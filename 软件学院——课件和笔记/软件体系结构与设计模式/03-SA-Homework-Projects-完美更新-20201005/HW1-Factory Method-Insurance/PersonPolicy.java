





public class PersonPolicy implements PolicyProducer {

   public AutoInsurance getAutoObj() {
      return new PersonInjur();
   }
}
