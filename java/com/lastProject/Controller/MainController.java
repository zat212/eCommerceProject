package com.lastProject.Controller;

import com.lastProject.Entity.*;
import com.lastProject.Exception.ProductNotFoundException;
import com.lastProject.Repository.OrderReportRepository;
import com.lastProject.Repository.OrderRepository;
import com.lastProject.Repository.StaffRepository;
import com.lastProject.Service.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.attribute.standard.PrinterURI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CardService cardService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderReportService orderReportService;

    @GetMapping("/index/{id}")
    public String showMain(Model model,@PathVariable int id){
        Customer foundCus = customerService.findById(id);
        model.addAttribute("customer",foundCus);
        model.addAttribute("id",id);
        return "index";
    }

    @GetMapping("/about/{id}")
    public String showAbout(Model model,@PathVariable int id){
        Customer foundCus = customerService.findById(id);
        model.addAttribute("customer",foundCus);
        model.addAttribute("id",id);
        return "about";
    }
    @PostMapping("/login")
    public String checkCustomer(@RequestParam(name = "email")String email,
                                @RequestParam(name = "password")String password,
                                RedirectAttributes redirectAttributes){
        Customer customer = customerService.findByemail(email);
        if(customer!=null && passwordEncoder.matches(password,customer.getCusPassword())){
            return "redirect:/main/index/"+customer.getCus_id();
        }
        else{
            redirectAttributes.addFlashAttribute("errorMessage","Password is incorrect,try again!");
            return "redirect:/main/login";}
    }
    @GetMapping("/shop/{id}")
    public String showShop(Model model,@PathVariable int id){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("customer",customerService.findById(id));
        model.addAttribute("id",id);
        model.addAttribute("products", products);
        return "shop";
    }

    @GetMapping("/shopSingle/{id}/{pid}")
    public String showSingle(@PathVariable("pid")int productId,
                             @PathVariable("id")int cusId,Model model){
        Product product = productService.getProductById(productId);
        Customer customer = customerService.findById(cusId);
        model.addAttribute("product",product);
        model.addAttribute("customer",customer);
        model.addAttribute("id",cusId);
        return "shop-single";
    }

    @PostMapping("/buy")
    public String addCart(@RequestParam("product-id") int productId,
                          @RequestParam("customer-id") int cusId,
                          @RequestParam("quantity") int productQuantity,
                          RedirectAttributes redirectAttributes) {
        try {
            cardService.addItemToCart(cusId, productId, productQuantity);
            redirectAttributes.addFlashAttribute("success", "Item successfully added to cart!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding item to cart: " + e.getMessage());
        }
        return "redirect:/main/shop/"+cusId;
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "productList";
    }

    @GetMapping("/contact")
    public String showContact(){
        return "contact";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }


    @GetMapping("/registration")
    public String showRegis(Model model){
        model.addAttribute("customer",new Customer());
        return "registration";
    }

    @GetMapping("/staffRegis")
    public String createStaff(Model model){
        model.addAttribute("staff",new Staff());
        return "staffRegis";
    }

    @PostMapping("/saveCus")
    public String saveCustomer(@ModelAttribute("customer")Customer customer, Model model, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("successMessage", "Your registration is successful");
        customerService.saveCustomer(customer);
        return "redirect:/main/login";
    }
    @GetMapping("/staffLogin")
    public String showTest(){
        return "adminLogin";
    }

    @PostMapping("/staffLogin")
    public String loginStaff(@RequestParam(name = "email")String email,
                             @RequestParam(name = "password")String password,
                             RedirectAttributes redirectAttributes){
        Staff staff = staffService.findByEmail(email);
        if (staff!=null && passwordEncoder.matches(password,staff.getStaffPassword())){
            return "redirect:/main/staffIndex";
        }else{
            redirectAttributes.addFlashAttribute("error","the error message!" );
            return "redirect:/main/staffLogin";
        }
    }
    @PostMapping("saveStaff")
    public String saveStaff(@ModelAttribute("staff")Staff staff, Model model,RedirectAttributes redirectAttributes){
        staffService.saveStaff(staff);
        return "redirect:/main/staffLogin";
    }

    @GetMapping("/addProduct")
    public String saveProduct(Model model){
        model.addAttribute("product",new Product());
        return "addProduct";
    }

    @PostMapping("/product/save")
    public String addProduct(Product product){
        productService.save(product);
        return "redirect:/main/staffProduct";
    }

    @PostMapping("/updateCartItem")
    public String updateCartItem(@RequestParam("orderItem-id") int orderItemId,
                                 @RequestParam("quantity") int quantity,
                                 @RequestParam("customer-id") int cusId,
                                 RedirectAttributes redirectAttributes) {
        try {
            orderItemService.updateOrderItemQuantity(orderItemId, quantity);
            redirectAttributes.addFlashAttribute("success", "Item quantity updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating item quantity: " + e.getMessage());
        }
        return "redirect:/main/showCartList/" + cusId;
    }

    @PostMapping("/deleteCartItem")
    public String deleteCartItem(@RequestParam("orderItem-id") int orderItemId,
                                 @RequestParam("customer-id") int cusId,
                                 RedirectAttributes redirectAttributes) {
        try {
            orderItemService.deleteOrderItem(orderItemId);
            redirectAttributes.addFlashAttribute("success", "Item removed from cart successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error removing item from cart: " + e.getMessage());
        }
        return "redirect:/main/showCartList/" + cusId;
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam("customerId") int customerId,
                           @RequestParam("billingName") String billingName,
                           @RequestParam("billingAddress") String billingAddress,
                           @RequestParam("billingEmail") String billingEmail,
                           @RequestParam("contactNumber1") String contactNumber1,
                           @RequestParam("contactNumber2") String contactNumber2,
                           RedirectAttributes redirectAttributes) {
        try {
            cardService.checkout(customerId, billingName, billingAddress, billingEmail, contactNumber1, contactNumber2);
            redirectAttributes.addFlashAttribute("success", "Successful Ordered!Thank U so much!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error" + e.getMessage());
        }
        return "redirect:/main/shop/"+customerId;
    }

    @GetMapping("/showCartList/{id}")
    public String showCart(Model model,@PathVariable int id){
        Customer cusCheckBill = customerService.findById(id);

        List<OrderItem> orderItemList = cardService.getProductsInCart(id);
        double totalPrice = orderItemList.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();
        model.addAttribute("customer", cusCheckBill);
        model.addAttribute("orderItems", orderItemList);
        model.addAttribute("totalPrice",totalPrice);
        return "checkCart";
    }

    @GetMapping("/staffIndex")
    public String showStaffIndex(
            @RequestParam(value = "date", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            Model model
    ) {
        if (date == null) {
            date = new Date();
        }
        List<OrderReport> reports = orderReportService.getReportsByDate(date);
        model.addAttribute("reports", reports);
        model.addAttribute("selectedDate", date);
        return "staffIndex";
    }

    @GetMapping("/manager/report/daily")
    public String showProductDaily(
            @RequestParam(value = "date", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            Model model
    ) {
        if (date == null) {
            date = new Date();
        }
        List<OrderItem> orderItems = orderItemService.getDailyReport(date);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("selectedDate", date);
        return "daily-report";
    }
    @GetMapping("/staffProduct")
    public String staffProduct(Model model){
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("products",productList);
        return "staffCheckProduct";
    }

    @GetMapping("/product/update/{id}")
    public String editProduct(@PathVariable("id")int id,Model model ){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @GetMapping("/manager/reports/monthly")
    public String getMonthlyReports(
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year,
            Model model
    ) {
        if (month == null || year == null) {
            LocalDate now = LocalDate.now();
            month = (month == null) ? now.getMonthValue() : month;
            year = (year == null) ? now.getYear() : year;
        }
        List<OrderReport> reports = orderReportService.getReportsByMonthAndYear(month, year, model);
        model.addAttribute("reports", reports);
        model.addAttribute("selectedMonth", month);
        model.addAttribute("selectedYear", year);
        return "monthly-reports";
    }
}
