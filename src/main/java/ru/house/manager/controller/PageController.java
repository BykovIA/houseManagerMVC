package ru.house.manager.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

import ru.house.manager.EntityDB.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.house.manager.serviceDB.*;
import ru.house.manager.Hash.*;

@Controller
public class PageController {

    public static int client_account_id = -1;
    public static int manager_id = -1;
    public static int request_context = -1;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String getLoginPage(Model model) {
        return "loginForm";
    }

    @RequestMapping(value="/user-profile", method = RequestMethod.POST)
    public String postLoginPage(@RequestParam(value="eMail") String eMail, @RequestParam(value="password") String password) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {

        String mail = new String(eMail.getBytes("ISO-8859-1"), "UTF-8");
        mail = mail.toLowerCase();
        AccountsService count = new AccountsService();
        if (count.emailCount(mail) != 1) { return "UserNotFound"; }

        AccountsService accountsService = new AccountsService();
        Accounts account = new Accounts();
        account = accountsService.getByEmail(mail);
        if (HashFunction.getHash(new String(password.getBytes("ISO-8859-1"), "UTF-8"), account.getSalt(), HashFunction.getSalt2()).equals(account.getHashPassword())) {
            client_account_id = account.getId();
            if(account.getResidentFlag() == 1) {
                return "userProFileForm";
            }
            if(account.getResidentFlag() == 0) {
                return "managerProFileForm";
            }
            else { return  "residentFlag not exst";}
        } else {
            return "loginForm";
        }
    }

    @RequestMapping(value = "/user-registration", method=RequestMethod.GET)
    public String getNewUserPage() {
        return "userRegistrationForm";
    }

    @RequestMapping(value="/user-registration", method=RequestMethod.POST)
    public String postNewUserPage(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="fatherName") String fatherName,
                                  @RequestParam(value="phoneNumb") String phoneNumber, @RequestParam(value="eMail") String eMail, @RequestParam(value="roomNumb") String roomNumber, @RequestParam(value="password") String password,
                                  @RequestParam(value="password2") String password2, @RequestParam(value="accessCode") int accessCode) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {

        if (!password.equals(password2)) {
            return "passwordNotEquals";
        } else {
            String mail = new String(eMail.getBytes("ISO-8859-1"), "UTF-8");
            mail = mail.toLowerCase();
            AccountsService count = new AccountsService();
            if (count.emailCount(mail) != 0) { return "ThisE-mailIsBusy"; }

            HousesService houseCount = new HousesService();
            if (houseCount.tokenCount(accessCode) != 1) { return "ThisAccessCodeNotExist"; }

            String salt1 = HashFunction.getSalt1();
            AccountsService accountsService = new AccountsService();
            Accounts account = new Accounts();
            account.seteMail(mail);
            account.setHashPassword(HashFunction.getHash(password, salt1, HashFunction.getSalt2()));
            account.setResidentFlag(1);
            account.setSalt(salt1);
            accountsService.add(account);

            AccountsService idAccount = new AccountsService();
            Accounts idacc = idAccount.getByEmail(mail);

            HousesService housesService = new HousesService();
            Houses house = housesService.getIdByToken(accessCode);

            UsersService usersService = new UsersService();
            Users user = new Users();
            user.setHouseId(house.getHouseId());
            user.setAccount_id(idacc.getId());
            user.setFirstName(new String(firstName.getBytes("ISO-8859-1"), "UTF-8"));
            user.setLastName(new String(lastName.getBytes("ISO-8859-1"), "UTF-8"));
            user.setFatherName(new String(fatherName.getBytes("ISO-8859-1"), "UTF-8"));
            user.setPhoneNumber(new String(phoneNumber.getBytes("ISO-8859-1"), "UTF-8"));
            user.setEmail(mail);
            user.setRoomNumber(new String(roomNumber.getBytes("ISO-8859-1"), "UTF-8"));
            usersService.add(user);

            return "loginForm";
        }
    }



    @RequestMapping(value = "/manager-registration", method=RequestMethod.GET)
    public String getNewManagerPage() {
        return "managerRegistrationForm";
    }

    @RequestMapping(value="/manager-registration", method=RequestMethod.POST)
    public String postNewManagerPage(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="fatherName") String fatherName,
                                  @RequestParam(value="phoneNumber") String phoneNumber, @RequestParam(value="eMail") String eMail, @RequestParam(value="companyName") String companyName,
                                  @RequestParam(value="password1") String password,
                                  @RequestParam(value="password2") String password2, @RequestParam(value="comment") String someInfo) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        Integer a= 0;
        if (!password.equals(password2)) {
            return "passwordNotEquals";
        } else {
            String mail = new String(eMail.getBytes("ISO-8859-1"), "UTF-8");
            mail = mail.toLowerCase();
            AccountsService count = new AccountsService();
            if (count.emailCount(mail) != 0) { return "ThisE-mailIsBusy"; }

            String salt1 = HashFunction.getSalt1();
            AccountsService accountsService = new AccountsService();
            Accounts account = new Accounts();
            account.seteMail(mail);
            account.setHashPassword(HashFunction.getHash(password, salt1, HashFunction.getSalt2()));
            account.setResidentFlag(0);
            account.setSalt(salt1);
            accountsService.add(account);

            AccountsService idAccount = new AccountsService();
            Accounts idacc = idAccount.getByEmail(mail);

            ManagersService managersService = new ManagersService();
            Managers manager = new Managers();
            manager.setCompanyName(new String(companyName.getBytes("ISO-8859-1"), "UTF-8"));
            manager.setFirstName(new String(firstName.getBytes("ISO-8859-1"), "UTF-8"));
            manager.setLastName(new String(lastName.getBytes("ISO-8859-1"), "UTF-8"));
            manager.setFatherName(new String(fatherName.getBytes("ISO-8859-1"), "UTF-8"));
            manager.setEmail(mail);
            manager.setPhoneNumber(new String(phoneNumber.getBytes("ISO-8859-1"), "UTF-8"));
            manager.setSomeInfo(new String(someInfo.getBytes("ISO-8859-1"), "UTF-8"));
            manager.setAccountId(idacc.getId());
            managersService.add(manager);

            return "redirect:/";
        }
    }


    @RequestMapping(value = "/house-registration", method=RequestMethod.GET)
    public String getNewHousePage() {
        return "managerHousesForm";
    }

    @RequestMapping(value="/house-registration", method=RequestMethod.POST)
    public String postNewHousePage(@RequestParam(value="city") String city, @RequestParam(value="address") String address, @RequestParam(value="ResidentsNumber") int residentsNumber) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {


        HousesService houseCount = new HousesService();
        if (houseCount.houseCount(address) != 0) { return "ThisHouseIsAlreadyExist"; }

            HousesService housesService = new HousesService();
            Houses house = new Houses();
            ManagersService managersService = new ManagersService();
            Managers manager = managersService.getByAccountId(client_account_id);
            manager_id = manager.getId();
            house.setManageCompanyId(manager_id);
            house.setAdress(new String(address.getBytes("ISO-8859-1"), "UTF-8"));
            house.setCity(new String(city.getBytes("ISO-8859-1"), "UTF-8"));
            house.setResidentsNumber(residentsNumber);
            house.setAccessToken(tokenGen());
            housesService.add(house);
            return "managerHousesForm";
    }

    private int tokenGen() throws SQLException {
        Random random=new Random();
        int rage=999999;
        int newToken = random.nextInt(rage);
        HousesService tokenCount = new HousesService();
        if (tokenCount.tokenCount(newToken) != 0) {
            newToken = tokenGen();
        }
        return newToken;
    }


    @RequestMapping(value = "/users-requests", method = RequestMethod.GET)
    public String getUsersRequestsPage() {
        return "userRequest";
    }


    @RequestMapping(value="/users-requests", method=RequestMethod.POST)
    public String postUsersRequestsPage(@RequestParam(value="text") String text) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {

        ApplicationsService applicationsService = new ApplicationsService();
        Applications application = new Applications();
        Users user = new Users();
        UsersService usersService = new UsersService();
        user = usersService.getById(client_account_id);
        application.setUserId(user.getId());
        HousesService housesService = new HousesService();
        Houses house = new Houses();
        house = housesService.getById(user.getHouseId());
        application.setManageId(house.getManageCompanyId());
        application.setImageName("NONE");
        application.setText(new String(text.getBytes("ISO-8859-1"), "UTF-8"));
        Date date = new Date();
        application.setData(date.toString());
        application.setStatus(Applications.STATUS_OPEN);
        applicationsService.add(application);

        return "userRequest";
    }

    @RequestMapping(value = "/manager-requests", method = RequestMethod.GET)
    public String getManagerRequestsPage() {
        return "managerRequest";
    }


    @RequestMapping(value="/manager-requests", method=RequestMethod.POST)
    public String postManagerRequestsPage(@RequestParam(value="button") String numb) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {


        int number = Integer.parseInt(numb);
        ApplicationsService applicationsService = new ApplicationsService();
        List<Applications> applicationsOpenList = new ArrayList<>();
        ManagersService managersService = new ManagersService();
        Managers manager = new Managers();
        manager = managersService.getByAccountId(PageController.client_account_id);
        applicationsOpenList = applicationsService.getAllForManager(manager.getId(), Applications.STATUS_OPEN);


        ApplicationsService applicationsService2 = new ApplicationsService();
        Applications application = new Applications();
        application.setStatus(Applications.STATUS_CLOSE);
        application.setApplicationsId(applicationsOpenList.get(number).getApplicationsId());


        applicationsService2.update(application);
        return "managerRequest";

    }


    @RequestMapping(value="/manager-requests-1", method=RequestMethod.POST)
    public String postbutton0 (@RequestParam(value="button0") String numb) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_context = 0;
        return "managerRequest";

    }


    @RequestMapping(value="/manager-requests-2", method=RequestMethod.POST)
    public String postbutton1(@RequestParam(value="button1") String numb) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_context = 1;
        return "managerRequest";

    }
}