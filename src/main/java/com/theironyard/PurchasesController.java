package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by scofieldservices on 12/26/16.
 */
@Controller
public class PurchasesController {
    @Autowired
    PurchaseRepository ECSpurchasesDB;
    @Autowired
    CustomerRepository ECSCustomerDB;

    @PostConstruct
    public void init() throws FileNotFoundException {
        //parses csv's only when repositories are empty,
        // loops over each line, parses each column into a customer or purchase object
        // then add to appropriaterepository

        if (ECSCustomerDB.count() == 0) {
            File customerFile = new File("customers.csv");
            Scanner s = new Scanner(customerFile);
            s.nextLine();
            while (s.hasNext()) {
                String lines = s.nextLine();
                String[] silo = lines.split(",");
                Customer c = new Customer();
                c.customerName = silo[0];
                c.customerEmail = silo[1];
                ECSCustomerDB.save(c);
            }
        }
        if (ECSpurchasesDB.count() == 0) {
            File purchaseFile = new File("purchases.csv");
            Scanner s = new Scanner(purchaseFile);
            s.nextLine();
            while (s.hasNext()) {
                String lines = s.nextLine();
                String[] silo = lines.split(",");
                Purchase p = new Purchase();
                p.date = silo[1];
                p.cardNumber = silo[2];
                p.cvv = Integer.valueOf(silo[3]);
                p.category = silo[4];
                int customerId = Integer.valueOf(silo[0]);
                p.customer = ECSCustomerDB.findOne(customerId);
                ECSpurchasesDB.save(p);
            }
        }

    }//end of init route

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model, String category, Integer page) {
        //display purchases
        //include name and email then category, cc#, cvv and date in joined table view
        //accepts category query parameter if category != null calls instead of findall
        //display links allowing to filter by each category
//        page = (page == null) ? 0 : page;
//        PageRequest pager = new PageRequest(page, 10);
        List<Purchase> pli;
        if(category != null) {
            pli = ECSpurchasesDB.findByCategory(category);
        } else {
            pli = (List<Purchase>) ECSpurchasesDB.findAll();
        }
        model.addAttribute("purchases", pli);
//        model.addAttribute("nextPage", page +1);
//        model.addAttribute("showNext", pli.hasNext());
//        model.addAttribute("previousPage", page -1);
//        model.addAttribute("showPrevious", pli.hasPrevious());
        model.addAttribute("category", category);
        return "home";
    }//end of slashroute
}
