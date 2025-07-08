package model;

import services.MailService;


public class EBook extends Book implements Sellable {
    private String fileType;

    public EBook(String ISBN, String title, String author, int year, double price, String fileType) {
        super(ISBN, title, author, year, price);
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

  @Override
    public boolean deliver(String email, int quantity) {
    MailService mailService = new MailService();

    // i think realistically we’d attach licenses or link or smth
    return mailService.sendEBook(email, getTitle(), getFileType());
    }

}
