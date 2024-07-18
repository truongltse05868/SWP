/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ContactDAO;
import entity.Contact;

/**
 *
 * @author Ngs-MT305
 */
public class ContactService extends BaseService {

    private final ContactDAO contactDAO;

    public ContactService() {
        this.contactDAO = new ContactDAO();
    }
    public boolean addContact(Contact contact){
        return contactDAO.addContact(contact);
    }
    public Contact getContactById(int contactId){
        return contactDAO.getContactById(contactId);
    }

}
