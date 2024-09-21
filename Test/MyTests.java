import org.junit.Assert;

public class MyTests {
    @org.junit.Test
    public void TestSomething(){
        Contact contact = new Contact("Brian", "Baecher", "9148064437", "44 main street");
        Assert.assertTrue(contact.getFirstName().equals("Brian"));
    }
}
