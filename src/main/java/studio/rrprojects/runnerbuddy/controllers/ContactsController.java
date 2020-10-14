package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.contacts.ContactContainer;

import javax.swing.*;
import java.util.ArrayList;

public class ContactsController {
    ArrayList<ContactContainer> contactList;

    public ContactsController(CharacterContainer characterContainer) {
        InitTable();
    }

    private void InitTable() {
        contactList = new ArrayList<>();
        contactList.add(new ContactContainer("Free #1", 1, true));
        contactList.add(new ContactContainer("Free #2", 1, true));
    }

    public DefaultListModel<String> getList() {
        DefaultListModel<String> list = new DefaultListModel<>();
        for (ContactContainer contact: contactList) {
            list.addElement(contact.StringFormat());
        }
        return list;
    }

    public ContactContainer getContact(String searchTerm) {
        for (ContactContainer contact : contactList) {
            if (contact.StringFormat().equalsIgnoreCase(searchTerm)) {
                return contact;
            }
        }
        return null;
    }

    public ContactContainer addNewContact() {
        String name = "Contact # " + (contactList.size() + 1);
        ContactContainer character = new ContactContainer(name, 1, false);
        contactList.add(character);
        return character;
    }

    public void DeleteContact(ContactContainer selectedContact) {
        if (selectedContact.isFree()) {
            return;
        }

        contactList.remove(selectedContact);
    }

    public String getLastContact() {
        return contactList.get(contactList.size()-1).StringFormat();
    }
}
