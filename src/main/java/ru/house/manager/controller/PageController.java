package ru.house.manager.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.sun.mail.iap.ByteArray;
import org.springframework.web.bind.annotation.PathVariable;
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
    public static int request_context = 0;
    public static int house_id = -1;
    public static int request_id = -1;
    public static String validator = "";
    public static Boolean validatorHasError = Boolean.FALSE;


    @RequestMapping(value="/", method=RequestMethod.GET)
    public String getLoginPage(Model model) {
        return "loginForm";
    }

    @RequestMapping(value="/user-profile", method = RequestMethod.POST)
    public String postLoginPage(@RequestParam(value="eMail") String eMail, @RequestParam(value="password") String password) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {

        String mail = new String(eMail.getBytes("ISO-8859-1"), "UTF-8");
        mail = mail.toLowerCase();
        AccountsService count = new AccountsService();
        if (count.emailCount(mail) != 1) {
            validator = "Пользователя с таким e-mail не существует!";
            validatorHasError = Boolean.TRUE;
            return "loginForm";
        }

        AccountsService accountsService = new AccountsService();
        Accounts account = new Accounts();
        account = accountsService.getByEmail(mail);
        if (HashFunction.getHash(new String(password.getBytes("ISO-8859-1"), "UTF-8"), account.getSalt(), HashFunction.getSalt2()).equals(account.getHashPassword())) {
            client_account_id = account.getId();
            if(account.getResidentFlag() == 1) {
                validatorHasError = Boolean.FALSE;
                return "userProFileForm";
            }
            if(account.getResidentFlag() == 0) {
                validatorHasError = Boolean.FALSE;
                return "managerProFileForm";
            }
            else {
                validatorHasError = Boolean.TRUE;
                validator = "Пользователь не идентифицирован по роли!";
                return  "loginForm";
            }
        } else {
            validatorHasError = Boolean.TRUE;
            validator = "Неизвестная ошибка :c";
            return "loginForm";
        }
    }

    @RequestMapping(value = "/manager-profile", method=RequestMethod.GET)
    public String getManagerProfile() {return "managerProFileForm";}

    @RequestMapping(value = "/resident-profile", method=RequestMethod.GET)
    public String getUserProfile() {return "userProFileForm";}

    @RequestMapping(value = "/user-registration", method=RequestMethod.GET)
    public String getNewUserPage() {
        return "userRegistrationForm";
    }

    @RequestMapping(value="/user-registration", method=RequestMethod.POST)
    public String postNewUserPage(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="fatherName") String fatherName,
                                  @RequestParam(value="phoneNumb") String phoneNumber, @RequestParam(value="eMail") String eMail, @RequestParam(value="roomNumb") String roomNumber, @RequestParam(value="password") String password,
                                  @RequestParam(value="password2") String password2, @RequestParam(value="accessCode") int accessCode) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {

        if (!password.equals(password2)) {
            validator = "Пароли не совпадают!";
            return "userRegistrationForm";
        } else {
            String mail = new String(eMail.getBytes("ISO-8859-1"), "UTF-8");
            mail = mail.toLowerCase();
            AccountsService count = new AccountsService();
            if (count.emailCount(mail) != 0) {
                validator = "Данный e-mail уже существует в системе!";
                return "userRegistrationForm";
            }

            HousesService houseCount = new HousesService();
            if (houseCount.tokenCount(accessCode) != 1) {
                validator = "Введенный вами токен доступа не существует!";
                return "userRegistrationForm";
            }

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
        return "managerHousesForm22";
    }

    @RequestMapping(value="/house-registration" , method=RequestMethod.POST)
    public String postNewHousePage(@RequestParam(value="city") String city, @RequestParam(value="street") String street, @RequestParam(value="house") String houseNumber, @RequestParam(value="flat") String flat, @RequestParam(value="ResidentsNumber") int residentsNumber) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {


        HousesService houseCount = new HousesService();
        String fullAddress = new String(street.getBytes("ISO-8859-1"), "UTF-8") + " " + new String(houseNumber.getBytes("ISO-8859-1"), "UTF-8") + " " + new String(flat.getBytes("ISO-8859-1"), "UTF-8");
        if (houseCount.houseCount(fullAddress) != 0) { return "ThisHouseIsAlreadyExist"; }

            HousesService housesService = new HousesService();
            Houses house = new Houses();
            ManagersService managersService = new ManagersService();
            Managers manager = managersService.getByAccountId(client_account_id);
            manager_id = manager.getId();
            house.setManageCompanyId(manager_id);
            house.setAdress(fullAddress);
            house.setCity(new String(city.getBytes("ISO-8859-1"), "UTF-8"));
            house.setResidentsNumber(residentsNumber);
            house.setAccessToken(tokenGen());
            housesService.add(house);
            return "managerHousesForm22";
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
        application.setHouse_id(user.getHouseId());
        application.setImageName("NONE");
        application.setText(new String(text.getBytes("ISO-8859-1"), "UTF-8"));
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        application.setData((dateFormat.format( date )).toString());
        application.setStatus(Applications.STATUS_OPEN);
        applicationsService.add(application);
        return "userRequest";
    }

    @RequestMapping(value="/users-requests-1", method=RequestMethod.POST)
    public String postUserButton0(@RequestParam(value="button0") String numb) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_context = 0;
        return "userRequest";
    }

    @RequestMapping(value="/users-requests-2", method=RequestMethod.POST)
    public String postUserButton1(@RequestParam(value="button1") String numb) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_context = 1;
        return "userRequest";
    }

    @RequestMapping(value="/users-requests-1", method=RequestMethod.POST)
    public String postUserButton0(@RequestParam(value="button0") String numb) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_context = 0;
        return "userRequest";
    }

    @RequestMapping(value="/users-requests-2", method=RequestMethod.POST)
    public String postUserButton1(@RequestParam(value="button1") String numb) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_context = 1;
        return "userRequest";
    }

        @RequestMapping(value = "/manager-requests", method = RequestMethod.GET)
    public String getManagerRequestsPage() {
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

    @RequestMapping(value ="/house-menu/{id}", method = RequestMethod.GET)
    public String getHouseMenu(@PathVariable int id) {
        house_id = id;
        return "houseMenu";
    }

    @RequestMapping(value ="/house-info/{id}", method = RequestMethod.GET)
    public String getHouseInfo(@PathVariable int id) {
        house_id = id;
        return "houseInfo";
    }

    @RequestMapping(value ="/house-requests/{id}", method = RequestMethod.GET)
    public String getHouseRequests(@PathVariable int id) {
        house_id = id;
        return "houseRequests";
    }

    @RequestMapping(value ="/house-ads/{id}", method = RequestMethod.GET)
    public String getHouseAds(@PathVariable int id) {
        house_id = id;
        return "houseAds";
    }

    @RequestMapping(value ="/house-info/{id}", method = RequestMethod.POST)
    public String postHouseInfo(@PathVariable int id, @RequestParam(value = "city") String city, @RequestParam(value = "street") String street, @RequestParam(value = "houseNumber") String houseNumber,
                                @RequestParam(value = "corpus") String corpus, @RequestParam(value = "residentNumber") int residentNumber, @RequestParam(value = "token") int token) throws UnsupportedEncodingException, SQLException {
        house_id = id;
        HousesService houseCount = new HousesService();
        String fullAddress = new String(street.getBytes("ISO-8859-1"), "UTF-8") + " " + new String(houseNumber.getBytes("ISO-8859-1"), "UTF-8") + " " + new String(corpus.getBytes("ISO-8859-1"), "UTF-8");
        if (houseCount.houseCount(fullAddress) != 0) { return "ThisHouseIsAlreadyExist"; }
        HousesService tokenService = new HousesService();
        HousesService housesService = new HousesService();
        Houses house = new Houses();
        ManagersService managersService = new ManagersService();
        Managers manager = managersService.getByAccountId(client_account_id);
        manager_id = manager.getId();
        house.setManageCompanyId(manager_id);
        house.setAdress(fullAddress);
        house.setCity(new String(city.getBytes("ISO-8859-1"), "UTF-8"));
        house.setResidentsNumber(residentNumber);
        HousesService tokenCount = new HousesService();
        if (tokenCount.tokenCount(token) != 0 && token != tokenService.getById(id).getAccessToken()) {
            return "tokenIsBusy";
        }
        house.setAccessToken(token);
        house.setHouseId(id);
        housesService.update(house);
        return "houseInfo";
    }

    @RequestMapping(value = "/manager-requests/{id}", method = RequestMethod.GET)
    public String getChosenRequest(@PathVariable int id) {
        request_id = id;
        return "managerRequest";
    }

    @RequestMapping(value="/manager-requests/{id}/comments", method=RequestMethod.POST)
    public String postManagerRequestCommentPage(@PathVariable int id, @RequestParam(value = "commentsText") String commentText) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_id = id;
        Comments comment = new Comments();
        CommentsService commentsService = new CommentsService();
        String text = new String(commentText.getBytes("ISO-8859-1"), "UTF-8");
        comment.setApplicationId(id);
        comment.setText(text);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        comment.setDate((dateFormat.format( date )).toString());
        ManagersService managersService = new ManagersService();
        Managers manager = new Managers();
        manager = managersService.getByAccountId(client_account_id);
        comment.setCommentator(manager.getCompanyName());
        commentsService.add(comment);
        return "managerRequest";
    }

    @RequestMapping(value="/manager-requests/{id}", method=RequestMethod.POST)
    public String postManagerRequestsPage(@PathVariable int id, @RequestParam(value = "commentsText") String commentText) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_id = id;
        ApplicationsService applicationsService2 = new ApplicationsService();
        Applications application = new Applications();
        application.setStatus(Applications.STATUS_CLOSE);
        application.setApplicationsId(id);
        applicationsService2.update(application);
        Comments comment = new Comments();
        CommentsService commentsService = new CommentsService();
        String text = new String(commentText.getBytes("ISO-8859-1"), "UTF-8");
        String text2 = "Ваша заявка была выполнена Управляющей компанией! \n" + text +"\nСпасибо за Ваше обращение.";
        comment.setApplicationId(id);
        comment.setText(text2);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        comment.setDate((dateFormat.format( date )).toString());
        ManagersService managersService = new ManagersService();
        Managers manager = new Managers();
        manager = managersService.getByAccountId(client_account_id);
        comment.setCommentator(manager.getCompanyName());
        commentsService.add(comment);
        return "managerRequest";
    }


    /**
     * Для домов
     */
    @RequestMapping(value = "/house-requests", method = RequestMethod.GET)
    public String getHouseRequestsPage() {
        return "houseRequests";
    }



    @RequestMapping(value="/house-requests-1", method=RequestMethod.POST)
    public String postbutton00 (@RequestParam(value="button0") String numb) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_context = 0;
        return "houseRequests";
    }


    @RequestMapping(value="/house-requests-2", method=RequestMethod.POST)
    public String postbutton11 (@RequestParam(value="button1") String numb) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_context = 1;
        return "houseRequests";
    }

    @RequestMapping(value = "/house-request/{id}", method = RequestMethod.GET)
    public String getChosenRequest1(@PathVariable int id) {
        request_id = id;
        return "houseRequests";
    }

    @RequestMapping(value="/house-requests/{id}/comments", method=RequestMethod.POST)
    public String postHouseRequestCommentPage(@PathVariable int id, @RequestParam(value = "commentsText") String commentText) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_id = id;
        Comments comment = new Comments();
        CommentsService commentsService = new CommentsService();
        String text = new String(commentText.getBytes("ISO-8859-1"), "UTF-8");
        comment.setApplicationId(id);
        comment.setText(text);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        comment.setDate((dateFormat.format( date )).toString());
        ManagersService managersService = new ManagersService();
        Managers manager = new Managers();
        manager = managersService.getByAccountId(client_account_id);
        comment.setCommentator(manager.getCompanyName());
        commentsService.add(comment);
        return "houseRequests";
    }

    @RequestMapping(value="/house-requests/{id}", method=RequestMethod.POST)
    public String postHouseRequestsPage(@PathVariable int id, @RequestParam(value = "commentsText") String commentText) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_id = id;
        ApplicationsService applicationsService2 = new ApplicationsService();
        Applications application = new Applications();
        application.setStatus(Applications.STATUS_CLOSE);
        application.setApplicationsId(id);
        applicationsService2.update(application);
        Comments comment = new Comments();
        CommentsService commentsService = new CommentsService();
        String text = new String(commentText.getBytes("ISO-8859-1"), "UTF-8");
        String text2 = "Ваша заявка была выполнена Управляющей компанией! \n" + text +"\nСпасибо за Ваше обращение.";
        comment.setApplicationId(id);
        comment.setText(text2);
        Date date = new Date();
        comment.setDate(date.toString());
        ManagersService managersService = new ManagersService();
        Managers manager = new Managers();
        manager = managersService.getByAccountId(client_account_id);
        comment.setCommentator(manager.getCompanyName());
        commentsService.add(comment);
        return "houseRequests";
    }

    /**
     * Для ЛСов
     */
    @RequestMapping(value="/user-requests/{id}", method=RequestMethod.POST)
    public String postUserRequestsPage(@PathVariable int id, @RequestParam(value = "commentsText") String commentText) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_id = id;
        ApplicationsService applicationsService2 = new ApplicationsService();
        Applications application = new Applications();
        application.setStatus(Applications.STATUS_CLOSE);
        application.setApplicationsId(id);
        applicationsService2.update(application);
        Comments comment = new Comments();
        CommentsService commentsService = new CommentsService();
        String text = new String(commentText.getBytes("ISO-8859-1"), "UTF-8");
        String text2 = "ЗАЯВКА ЗАКРЫТА!\n" + text;
        comment.setApplicationId(id);
        comment.setText(text2);
        Date date = new Date();
        comment.setDate(date.toString());
        UsersService usersService = new UsersService();
        Users user = new Users();
        user = usersService.getById(client_account_id);
        String writer = user.getFirstName() + " " + user.getLastName();
        comment.setCommentator(writer);
        commentsService.add(comment);
        return "userRequest";
    }


    @RequestMapping(value="/user-requests/{id}/comments", method=RequestMethod.POST)
    public String postUserRequestCommentPage(@PathVariable int id, @RequestParam(value = "commentsText") String commentText) throws UnsupportedEncodingException, SQLException, NoSuchAlgorithmException {
        request_id = id;
        Comments comment = new Comments();
        CommentsService commentsService = new CommentsService();
        String text = new String(commentText.getBytes("ISO-8859-1"), "UTF-8");
        comment.setApplicationId(id);
        comment.setText(text);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        comment.setDate((dateFormat.format( date )).toString());
        UsersService usersService = new UsersService();
        Users user = new Users();
        user = usersService.getById(client_account_id);
        String writer = user.getFirstName() + " " + user.getLastName();
        comment.setCommentator(writer);
        commentsService.add(comment);
        return "userRequest";
    }

    @RequestMapping(value = "/user-requests/{id}", method = RequestMethod.GET)
    public String getChosenRequest2(@PathVariable int id) {
        request_id = id;
        return "userRequest";
    }

    @RequestMapping(value ="/house-ads/{id}", method = RequestMethod.POST)
    public String postHouseAds(@PathVariable int id, @RequestParam(value = "text") String text, @RequestParam(value = "status") String status) throws UnsupportedEncodingException, SQLException {
        house_id = id;
        Notifications notification = new Notifications();
        notification.setManagerId(manager_id);
        notification.setHouseId(house_id);
        notification.setStatus(status);
        notification.setUserVisibility(0);
        String text2 = new String(text.getBytes("ISO-8859-1"), "UTF-8");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        notification.setDate((dateFormat.format( date )).toString());
        notification.setText(text2);
        NotificationsService notificationsService = new NotificationsService();
        notificationsService.add(notification);
        return "houseAds";
    }

    @RequestMapping(value = "/users-ads", method = RequestMethod.GET)
    public String getUsersAds() {
        return "userAds";
    }
}