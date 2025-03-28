package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class  AddNewContactTests extends TestBase{

    @BeforeClass
    public void preCondition(){
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("eldar@mail.com").withPassword("Aa123456!"));
        }

    }

    @Test
    public void  addContactSuccessAllFields(){
        int i= new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Stark")
                .address("NY")
                .phone("34343434"+i)
                .email("stark"+i+"@gmail.com")
                .description("The best")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));


    }
    @Test
    public void  addContactSuccessRequiredFields(){
        int i= new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Tony"+i)
                .lastName("Stark")
                .address("NY")
                .phone("34343434"+i)
                .email("stark"+i+"@gmail.com")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addNewContactWrongName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Stark")
                .address("NY")
                .phone("3434343434")
                .email("stark@gmail.com")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());

    }
    @Test
    public void  addNewContactWrongAddress(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Stark")
                .address("")
                .phone("3434343434")
                .email("stark@gmail.com")
                .description("The best")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());

    }

    @Test
    public void addNewContactWrongLastName(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("")
                .address("NY")
                .phone("3434343434")
                .email("stark@gmail.com")
                .description("The best")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
    }
    @Test
    public void addNewContactWrongPhone(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Stark")
                .address("NY")
                .phone("")
                .email("stark@gmail.com")
                .description("The best")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));


    }
    @Test
    public void addNewContactWrongEmail(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Stark")
                .address("NY")
                .phone("1234567891234")
                .email("starkgmail.com")
                .description("The best")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Email not valid: must be a well-formed email address"));

    }


    // "Contact added" eql "Contact added"
    // "Contact  with ID : 123456 was added" contains
}
