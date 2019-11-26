package ru.house.manager.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.http.converter.json.GsonBuilderUtils;
import ru.house.manager.EntityDB.Houses;
import ru.house.manager.EntityDB.Managers;
import ru.house.manager.EntityDB.Users;
import ru.house.manager.EntityDB.Accounts;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.house.manager.serviceDB.AccountsService;
import ru.house.manager.serviceDB.HousesService;
import ru.house.manager.serviceDB.ManagersService;
import ru.house.manager.serviceDB.UsersService;
import ru.house.manager.Hash.*;

@Controller
public class PageController {

    public static int client_account_id = -1;
    public static int manager_id = -1;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String getLoginPage(Model model) {
        return "loginForm";
    }

    @RequestMapping(value="/user-profile", method = RequestMethod.POST)
    public String postLoginPage(@RequestParam(value="eMail") String eMail, @RequestParam(value="password") String password) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {

        AccountsService accountsService = new AccountsService();
        Accounts account = new Accounts();
        account = accountsService.getByEmail(new String(eMail.getBytes("ISO-8859-1"), "UTF-8"));
        if (HashFunction.getHash(new String(password.getBytes("ISO-8859-1"), "UTF-8"), account.getSalt(), HashFunction.getSalt2()).equals(account.getHashPassword())) {
            client_account_id = account.getId();
            if(account.getResidentFlag() == 1) {
                return "userProFileForm";
            }
            if(account.getResidentFlag() == 0) {
                return "managerMainForm";
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
            String salt1 = HashFunction.getSalt1();
            AccountsService accountsService = new AccountsService();
            Accounts account = new Accounts();
            account.seteMail(new String(eMail.getBytes("ISO-8859-1"), "UTF-8"));
            account.setHashPassword(HashFunction.getHash(password, salt1, HashFunction.getSalt2()));
            account.setResidentFlag(1);
            account.setSalt(salt1);
            accountsService.add(account);

            AccountsService idAccount = new AccountsService();
            Accounts idacc = idAccount.getByEmail(new String(eMail.getBytes("ISO-8859-1"), "UTF-8"));

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
            user.setEmail(new String(eMail.getBytes("ISO-8859-1"), "UTF-8"));
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
                                  @RequestParam(value="phoneNumb") String phoneNumber, @RequestParam(value="eMail") String eMail, @RequestParam(value="companyName") String companyName,
                                  @RequestParam(value="login") String login, @RequestParam(value="password") String password,
                                  @RequestParam(value="password2") String password2, @RequestParam(value="someInfo") String someInfo) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {

        if (!password.equals(password2)) {
            return "passwordNotEquals";
        } else {
            String salt1 = HashFunction.getSalt1();
            AccountsService accountsService = new AccountsService();
            Accounts account = new Accounts();
            account.seteMail(new String(eMail.getBytes("ISO-8859-1"), "UTF-8"));
            account.setHashPassword(HashFunction.getHash(password, salt1, HashFunction.getSalt2()));
            account.setResidentFlag(0);
            account.setSalt(salt1);
            accountsService.add(account);

            AccountsService idAccount = new AccountsService();
            Accounts idacc = idAccount.getByEmail(new String(eMail.getBytes("ISO-8859-1"), "UTF-8"));

            ManagersService managersService = new ManagersService();
            Managers manager = new Managers();
            manager.setCompanyName(new String(companyName.getBytes("ISO-8859-1"), "UTF-8"));
            manager.setFirstName(new String(firstName.getBytes("ISO-8859-1"), "UTF-8"));
            manager.setLastName(new String(lastName.getBytes("ISO-8859-1"), "UTF-8"));
            manager.setFatherName(new String(fatherName.getBytes("ISO-8859-1"), "UTF-8"));
            manager.setEmail(new String(eMail.getBytes("ISO-8859-1"), "UTF-8"));
            manager.setPhoneNumber(new String(phoneNumber.getBytes("ISO-8859-1"), "UTF-8"));
            manager.setSomeInfo(new String(someInfo.getBytes("ISO-8859-1"), "UTF-8"));
            manager.setAccountId(idacc.getId());
            managersService.add(manager);

            return "redirect:/";
        }
    }


    @RequestMapping(value = "/house-registration", method=RequestMethod.GET)
    public String getNewHousePage() {
        return "houseRegistrationForm";
    }

    @RequestMapping(value="/house-registration", method=RequestMethod.POST)
    public String postNewHousePage(@RequestParam(value="city") String city, @RequestParam(value="address") String address, @RequestParam(value="ResidentsNumber") int residentsNumber) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {

            HousesService housesService = new HousesService();
            Houses house = new Houses();
            ManagersService managersService = new ManagersService();
            Managers manager = managersService.getByAccountId(client_account_id);
            manager_id = manager.getId();
            house.setManageCompanyId(manager_id);
            house.setAdress(new String(address.getBytes("ISO-8859-1"), "UTF-8"));
            house.setCity(new String(city.getBytes("ISO-8859-1"), "UTF-8"));
            house.setResidentsNumber(residentsNumber);
            housesService.add(house);
            return "houseRegistrationForm";
    }





    @RequestMapping(value="/user-profile", method=RequestMethod.GET)
    public String getUserProfilePage(Model model) {
        return "userProFileForm";
    }

    /*@RequestMapping(value="/user-profile", method = RequestMethod.POST)
    public String postUserProfilePage(@RequestParam(value="eMail") String eMail, @RequestParam(value="password") String password) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {

        AccountsService accountsService = new AccountsService();
        Accounts account = new Accounts();
        account = accountsService.getByEmail(new String(eMail.getBytes("ISO-8859-1"), "UTF-8"));
        if (HashFunction.getHash(new String(password.getBytes("ISO-8859-1"), "UTF-8"), account.getSalt(), HashFunction.getSalt2()).equals(account.getHashPassword())) {
            client_account_id = account.getId();
            if(account.getResidentFlag() == 1) {
                return "userProFileForm";
            }
            if(account.getResidentFlag() == 0) {
                return "managerMainForm";
            }
            else { return  "residentFlag not exst";}
        } else {
            return "userProFileForm";
        }
    }*/

}