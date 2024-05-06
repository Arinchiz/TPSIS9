
Тестируемый код для функционального требования FR1-1 представлен ниже.

@PostMapping("/addCar")
public String addCar(Model model, @RequestParam String brand, @RequestParam
String model, @RequestParam int year, @RequestParam String vin) {
        try {
        // Проверка наличия обязательных данных
        if (brand.isEmpty() || model.isEmpty() || vin.isEmpty()) {
        model.addAttribute("message", "Некорректные данные!");
        return "addCarForm";
        }

        Car newCar = new Car(brand, model, year, vin);
        carRepo.save(newCar);
        return "redirect:/cars";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при добавлении автомобиля!");
        return "addCarForm";
        }
        }
        Тестирующий код для функционального требования FR1-1 представлен ниже.

@Test
public void testAddCar() throws Exception {
        String brand = "Toyota";
        String model = "Camry";
        int year = 2022;
        String vin = "1234567890";

        mockMvc.perform(MockMvcRequestBuilders.post("/addCar")
        .param("brand", brand)
        .param("model", model)
        .param("year", String.valueOf(year))
        .param("vin", vin))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/cars"));
        }

        Тестируемый код для функционального требования FR1-2 представлен ниже.

@PostMapping("/addCarModel")
public String addCarModel(Model model, @RequestParam String modelName, @RequestParam String manufacturerCountry) {
        try {
        // Проверка наличия обязательных данных
        if (modelName.isEmpty() || manufacturerCountry.isEmpty()) {
        model.addAttribute("message", "Некорректные данные!");
        return "addCarModelForm";
        }

        // Другие операции сохранения данных
        // carModelRepo.save(newCarModel);
        return "redirect:/carModels";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при добавлении модели
        автомобиля!");
        return "addCarModelForm";
        }
        }

        Тестирующий код для функционального требования FR1-2 представлен ниже.
@Test
public void testAddCarModelWithCountry() throws Exception {
        String modelName = "Corolla";
        String manufacturerCountry = "Japan";
        mockMvc.perform(MockMvcRequestBuilders.post("/addCarModel")
        .param("modelName", modelName)
        .param("manufacturerCountry", manufacturerCountry))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/carModels"))
        .andExpect(MockMvcResultMatchers.flash().attributeExists
        ("message"));
        }

        Тестируемый код для функционального требования FR2-1 представлен ниже.

@PostMapping("/submitRequest")
public String submitRequest(Model model, @RequestParam Long carId,
@RequestParam String name, @RequestParam String phoneNumber,
@RequestParam String email, @RequestParam String additionalComments) {
        try {

        if (name.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
        model.addAttribute("message", "Некорректные данные!");
        return "requestForm";
        }

        Car car = carRepo.findById(carId).orElse(null);
        if (car == null) {
        model.addAttribute("message", "Выбранный автомобиль не найден!");
        return "requestForm";
        }

        PurchaseRequest request = new PurchaseRequest(car, name, phoneNumber, email, additionalComments);
        purchaseRequestRepo.save(request);
        return "redirect:/confirmation";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при оформлении заявки!");
        return "requestForm";
        }
        }

        Тестирующий код для функционального требования FR2-1 представлен ниже.

@Test
public void testSubmitRequest() throws Exception {
        Long carId = 1L;
        String name = "John Doe";
        String phoneNumber = "123-456-7890";
        String email = "john@example.com";
        String additionalComments = "I'm interested in purchasing this car.";

        mockMvc.perform(MockMvcRequestBuilders.post("/submitRequest")
        .param("carId", String.valueOf(carId))
        .param("name", name)
        .param("phoneNumber", phoneNumber)
        .param("email", email)
        .param("additionalComments", additionalComments))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl
        ("/confirmation"));
        }

        Тестируемый код для функционального требования FR2-2 представлен ниже.

@PostMapping("/addCarModel")
public String addCarModel(Model model, @RequestParam String modelName, @RequestParam String manufacturerCountry) {
        try {
        // Проверка наличия обязательных данных
        if (modelName.isEmpty() || manufacturerCountry.isEmpty()) {
        model.addAttribute("message", "Некорректные данные!");
        return "addCarModelForm";
        }

        // Другие операции сохранения данных
        // carModelRepo.save(newCarModel);
        return "redirect:/carModels";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при добавлении модели автомобиля!");
        return "addCarModelForm";
        }
        }

        Тестирующий код для функционального требования FR2-2 представлен ниже.

@Test
public void testAddCarModelWithCountry() throws Exception {
        String modelName = "Corolla";
        String manufacturerCountry = "Japan";
        mockMvc.perform(MockMvcRequestBuilders.post("/addCarModel")
        .param("modelName", modelName)
        .param("manufacturerCountry", manufacturerCountry))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/carModels"))
        .andExpect(MockMvcResultMatchers.flash().attributeExists("message"));
        }

        Тестируемый код для функционального требования FR3-1 представлен ниже.

@PostMapping("/addModel")
public String addModel(Model model, @RequestParam String brand,
@RequestParam String modelName, @RequestParam int year,
@RequestParam String description) {
        try {
        // Проверка наличия обязательных данных
        if (brand.isEmpty() || modelName.isEmpty()) {
        model.addAttribute("message", "Некорректные данные!");
        return "addModelForm";
        }

        CarModel newModel = new CarModel(brand, modelName, year, description);
        carModelRepo.save(newModel);
        return "redirect:/models";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при добавлении
        модели автомобиля!");
        return "addModelForm";
        }
        }

        Тестируемый код для функционального требования FR3-1 представлен ниже.

@Test
public void testAddModel() throws Exception {
        String brand = "Toyota";
        String modelName = "Camry";
        int year = 2022;
        String description = "Mid-size sedan";

        mockMvc.perform(MockMvcRequestBuilders.post("/addModel")
        .param("brand", brand)
        .param("modelName", modelName)
        .param("year", String.valueOf(year))
        .param("description", description))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/models"));
        }

        Тестируемый код для функционального требования FR3-2 представлен ниже.

@PostMapping("/addCarModel")
public String addCarModel(Model model, @RequestParam String modelName, @RequestParam String category) {
        try {
        // Проверка наличия обязательных данных
        if (modelName.isEmpty() || category.isEmpty()) {
        model.addAttribute("message", "Некорректные данные!");
        return "addCarModelForm";
        }

        // Другие операции сохранения данных
        // carModelRepo.save(newCarModel);
        return "redirect:/carModels";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при добавлении
        модели автомобиля!");
        return "addCarModelForm";
        }
        }

        Тестирующий код для функционального требования FR3-2 представлен ниже.

@Test
public void testAddCarModelWithCategory() throws Exception {
        String modelName = "Civic";
        String category = "Седан";

        mockMvc.perform(MockMvcRequestBuilders.post("/addCarModel")
        .param("modelName", modelName)
        .param("category", category))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/carModels"))
        .andExpect(MockMvcResultMatchers.flash().attributeExists
        ("message"));
        }

        Тестируемый код для функционального требования FR4-1 представлен ниже.

@GetMapping("/cars")
public String viewAllCars(Model model) {
        try {
        // Получение полного списка автомобилей из базы данных
        List<Car> allCars = carRepo.findAll();
        model.addAttribute("cars", allCars);
        return "carsList";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при получении списка
        автомобилей!");
        return "errorPage";
        }
        }

        Тестирующий код для функционального требования FR4-1 представлен ниже.

@Test
public void testAddCarModelWithCategory() throws Exception {
        String modelName = "Civic";
        String category = "Седан";
        mockMvc.perform(MockMvcRequestBuilders.post("/addCarModel")
        .param("modelName", modelName)
        .param("category", category))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/carModels"))
        .andExpect(MockMvcResultMatchers.flash()
        .attributeExists("message"));
        }

        Тестируемый код для функционального требования FR4-2 представлен ниже.

@GetMapping("/cars")
public String getCars(Model model, @RequestParam(required = false) String brand, @RequestParam(required = false) String model, @RequestParam(required = false) Integer year, @RequestParam(required = false) Double price) {
        try {
        List<Car> cars = carService.getCarsByFilters(brand, model, year, price);
        model.addAttribute("cars", cars);
        return "carsList";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при
        получении списка автомобилей!");
        return "errorPage";
        }
        }

        Тестирующий код для функционального требования FR4-2 представлен ниже.

@Test
public void testGetCarsByFilters() throws Exception {
        String brand = "Toyota";
        String model = "Camry";
        int year = 2022;
        double price = 25000.00;

        mockMvc.perform(MockMvcRequestBuilders.get("/cars")
        .param("brand", brand)
        .param("model", model)
        .param("year", String.valueOf(year))
        .param("price", String.valueOf(price)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("carsList"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("cars"));
        }

        Тестируемый код для функционального требования FR5-1 представлен ниже.

        import org.junit.jupiter.api.Test;
        import org.mockito.Mockito;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoCockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.mock.mockito.MockBean;
        import org.springframework.test.web.servlet.MockMvc;
        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
        import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

        import java.util.Arrays;
        import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarRepository carRepo;

    @Test
    public void testViewAllCars() throws Exception {
        List<Car> mockCars = Arrays.asList(
                new Car("Toyota", "Camry", 2022, "VIN1234567890"),
                new Car("Honda", "Accord", 2023, "VIN0987654321")
        );

        Mockito.when(carRepo.findAll()).thenReturn(mockCars);

        mockMvc.perform(MockMvcRequestBuilders.get("/cars"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("cars"))
                .andExpect(MockMvcResultMatchers.view().name("carsList"))
                .andExpect(MockMvcResultMatchers.model().attribute("cars", mockCars));
    }
}

    Тестирующий код для функционального требования FR5-1 представлен ниже.

@Test
public void testGetCarsByFilters() throws Exception {
        String brand = "Toyota";
        String model = "Camry";
        int year = 2022;
        double price = 25000.00;

        mockMvc.perform(MockMvcRequestBuilders.get("/cars")
        .param("brand", brand)
        .param("model", model)
        .param("year", String.valueOf(year))
        .param("price", String.valueOf(price)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("carsList"))
        .andExpect(MockMvcResultMatchers.model()s("cars"));
        }

        Тестируемый код для функционального требования FR5-2 представлен ниже.

@GetMapping("/accounts")
public String getAccountsByUserType(Model model, @RequestParam
        (required = false) String userType) {
        try {
        List<Account> accounts = accountService.getAccountsByUserType
        (userType);
        model.addAttribute("accounts", accounts);
        return "accountsList";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при получении списка
        аккаунтов!");
        return "errorPage";
        }
        }

        Тестирующий код для функционального требования FR5-2 представлен ниже.

@Test
public void testGetAccountsByUserType() throws Exception {
        String userType = "admin";

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts")
        .param("userType", userType))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("accountsList"))
        .andExpect(MockMvcResultMatchers.model().attributeExists
        ("accounts"));
        }

        Тестирующий код для функционального требования FR6-1 представлен ниже.

@Test
public void testGetCarsByFilters() throws Exception {
        String brand = "Toyota";
        String model = "Camry";
        int year = 2022;
        double price = 25000.00;

        mockMvc.perform(MockMvcRequestBuilders.get("/cars")
        .param("brand", brand)
        .param("model", model)
        .param("year", String.valueOf(year))
        .param("price", String.valueOf(price)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("carsList"))
        .andExpect(MockMvcResultMatchers.model().aists("cars"));
        }

        Тестируемый код для функционального требования FR6-1 представлен ниже.

        import org.junit.jupiter.api.Test; import org.mockito.InjectMocks; import org.mockito.Mock;
        import org.springframework.beans.factory.annotation.Autowired; @Test
public void testApproveRequest() throws Exception {
        Long requestId = 1L;
        PurchaseRequest mockRequest = new PurchaseRequest(/*здесь параметры заявки*/);
        mockRequest.setStatus("Pending");

        Mockito.when(purchaseRequestRepo.findById(requestId)).thenReturn(Optional.of(mockRequest));

        mockMvc.perform(MockMvcRequestBuilders.post("/approveRequest")
        .param("requestId", String.valueOf(requestId)))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/requests"));

        // Проверка, что статус заявки изменился на "Одобрено"
        Assertions.assertEquals("Approved", mockRequest.getStatus());
        }

        Тестируемый код для функционального требования FR6-2 представлен ниже.

        import org.junit.jupiter.api.Test; import org.mockito.InjectMocks; import org.mockito.Mock;
        import org.springframework.beans.factory.annotation.Autowired; @Test
public void testApproveRequest() throws Exception {
        Long requestId = 1L;
        PurchaseRequest mockRequest = new PurchaseRequest(/*здесь параметры заявки*/);
        mockRequest.setStatus("Pending");   Mockito.when(purchaseRequestRepo.findById(requestId)).thenReturn(Optional.of(mockRequest));

        mockMvc.perform(MockMvcRequestBuilders.post("/approveRequest")
        .param("requestId", String.valueOf(requestId)))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/requests"));

        // Проверка, что статус заявки изменился на "Одобрено"
        Assertions.assertEquals("Approved", mockRequest.getStatus());
        }

        Тестирующий код для функционального требования FR6-2 представлен ниже.

@Test
public void testViewRequestDetails() throws Exception {
        Long requestId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/viewRequest/{requestId}", requestId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("requestDetails"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("request"));
        }

        Тестируемый код для функционального требования FR7-1 представлен ниже.

@GetMapping("/profile/{userId}")
public String viewUserProfile(Model model, @PathVariable Long userId) {
        try {
        // Получение информации о пользователе из базы данных
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
        model.addAttribute("message", "Пользователь не найден!");
        return "errorPage";
        }

        model.addAttribute("user", user);
        return "userProfile";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при просмотре профиля пользователя!");
        return "errorPage";
        }
        }

        Тестирующий код для функционального требования FR7-1 представлен ниже.

@Test
public void testViewUserProfile() throws Exception {
        Long userId = 1L;
        User mockUser = new User(/*здесь параметры пользователя*/);

        Mockito.when(userRepository.findById(userId)).thenReturn
        (Optional.of(mockUser));

        mockMvc.perform(MockMvcRequestBuilders.get("/profile/{userId}",))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model()
        .andExpect(MockMvcResultMatchers.view()
        .andExpect(MockMvcResultMatchers.model().attribute("user", mockUser));
        }

        Тестируемый код для функционального требования FR7-2 представлен ниже.

@GetMapping("/profile")
public String viewUserProfile(Model model) {
        try {
        // Логика получения личных данных пользователя
        UserProfile userProfile = userService.getUserProfile();

        model.addAttribute("userProfile", userProfile);
        return "userProfile";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при просмотре профиля пользователя!");
        return "errorPage";
        }
        }

@PostMapping("/profile")
public String updateUserProfile(Model model, @ModelAttribute UserProfile userProfile) {
        try {
        // Логика обновления личных данных пользователя
        userService.updateUserProfile(userProfile);

        model.addAttribute("message", "Профиль успешно обновлен!");
        return "userProfile";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при обновлении профиля пользователя!");
        return "userProfile";
        }

        }

        Тестирующий код для функционального требования FR7-2 представлен ниже.

@Test
public void testViewAndEditUserProfile() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName("John");
        userProfile.setLastName("Doe");
        userProfile.setEmail("john.doe@example.com");

        mockMvc.perform(MockMvcRequestBuilders.get("/profile"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("userProfile"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("userProfile"));

        mockMvc.perform(MockMvcRequestBuilders.post("/profile")
        .flashAttr("userProfile", userProfile))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("userProfile"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("message"));
        }

        Тестируемый код для функционального требования FR8-1 представлен ниже.

@GetMapping("/report/{reportId}")
public String viewReport(Model model, @PathVariable Long reportId) {
        try {
        Report report = reportRepository.findById(reportId).orElse(null);
        if (report == null) {
        model.addAttribute("message", "Отчет не найден!");
        return "errorPage";
        }

        model.addAttribute("report", report);
        return "viewReport";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при просмотре отчета!");
        return "errorPage";
        }
        }

        Тестирующий код для функционального требования FR8-1 представлен ниже.

@Test
public void testViewReport() throws Exception {
        Long reportId = 1L;
        Report mockReport = new Report(/*здесь параметры отчета*/);

        Mockito.when(reportRepository.findById(reportId)).thenReturn(Optional.of(mockReport));

        mockMvc.perform(MockMvcRequestBuilders.get("/report/{reportId}", reportId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().attributeExists("report"))
        .andExpect(MockMvcResultMatchers.view().name("viewReport"))
        .andExpect(MockMvcResultMatchers.model().attribute("report", mockReport));
        }

        Тестируемый код для функционального требования FR8-1 представлен ниже.

@GetMapping("/salesReport")
public String generateSalesReport(Model model) {
        try {
        // Логика генерации отчета о продажах с разбивкой по типам
        автомобилей
        SalesReport salesReport = reportService.generateSalesRepo
        rtByCarType();
        model.addAttribute("salesReport", salesReport);
        return "salesReport";
        } catch (Exception e) {
        return "errorPage";
        }
        }

        Тестирующий код для функционального требования FR8-2 представлен ниже.

@Test
public void testGenerateSalesReport() throws Exception {
        SalesReport salesReport = new SalesReport();
        salesReport.addSale("SUV", 10);
        salesReport.addSale("Sedan", 15);
        salesReport.addSale("Hatchback", 5);

        mockMvc.perform(MockMvcRequestBuilders.get("/salesReport"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("salesReport"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("salesReport"));
        }

        Тестируемый код для функционального требования FR9-1 представлен ниже.

@PostMapping("/editCar/{carId}")
public String editCar(Model model, @PathVariable Long carId,
@RequestParam String brand, @RequestParam String model,
@RequestParam int year, @RequestParam String vin) {
        try {

        Car car = carRepository.findById(carId).orElse(null);
        if (car == null) {
        model.addAttribute("message", "Автомобиль не найден!");
        return "errorPage";
        }

        car.setBrand(brand);
        car.setModel(model);
        car.setYear(year);
        car.setVin(vin);
        carRepository.save(car);

        return "redirect:/cars";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при редактировании
        автомобиля!");
        return "errorPage";
        }

        Тестирующий код для функционального требования FR9-1 представлен ниже.

@Test
public void testEditCar() throws Exception {
        Long carId = 1L;
        String newBrand = "Toyota";
        String newModel = "Corolla";
        int newYear = 2023;
        String newVin = "VIN9876543210";

        Car mockCar = new Car(/*здесь параметры существующего автомобиля*/);

        Mockito.when(carRepository.findById(carId)).

        mockMvc.perform(MockMvcRequestBuilders.post("/editCar/{carId}", carId)
        .param("brand", newBrand)
        .param("model", newModel)
        .param("year", String.valueOf(newYear))
        .param("vin", newVin))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/cars"));

        Assertions.assertEquals(newBrand, mockCar.getBrand());
        Assertions.assertEquals(newModel, mockCar.getModel());
        Assertions.assertEquals(newYear, mockCar.getYear());
        Assertions.assertEquals(newVin, mockCar.getVin());

        Тестируемый код для функционального требования FR9-2 представлен ниже.

@GetMapping("/carHistory/{carId}")
public String viewCarHistory(Model model, @PathVariable Long carId) {
        try {
        // Логика получения истории изменений данных об автомобиле
        List<CarHistoryEntry> carHistory = carService.getCarHistory(carId);

        model.addAttribute("carHistory", carHistory);
        return "carHistory";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка  данных об автомобиле!");
        return "errorPage";
        }
        }

        Тестирующий код для функционального требования FR9-2 представлен ниже.

@Test
public void testViewCarHistory() throws Exception {
        Long carId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/carHistory/{carId}", carId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("carHistory"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("carHistory"));
        }

        Тестируемый код для функционального требования FR10-1 представлен ниже.

@GetMapping("/downloadReport/{reportId}")
public ResponseEntity<byte[]> downloadReport(@PathVariable Long reportId) {
        try {
        Report report = reportRepository.findById(reportId).orElse(null);
        if (report == null) {
        return ResponseEntity.notFound().build();
        }

        byte[] pdfContent = generatePdf(report);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "report.pdf");
        headers.setContentLength(pdfContent.length);

        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        }
        Тестирующий код для функционального требования FR10-1 представлен ниже.

@Test
public void testDownloadReport() throws Exception {
        Long reportId = 1L;
        Report mockReport = new Report(/*здесь параметры отчета*/);   Mockito.when(reportRepository.findById(reportId)).thenReturn(Optional.of(mockReport));
        Mockito.when(reportService.generatePdf(mockReport)).thenReturn(/*здесь моковое содержимое PDF-файла*/);

        mockMvc.perform(MockMvcRequestBuilders.get("/downloadReport/{reportId}", reportId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_PDF));
        }

        Тестируемый код для функционального требования FR10-2 представлен ниже.

@GetMapping("/previewReport/{reportId}")
public String previewReport(Model model, @PathVariable Long reportId) {
        try {
        // Логика получения предварительного просмотра отчета
        Report report = reportService.getReportById(reportId);

        model.addAttribute("report", report);
        return "previewReport";
        } catch (Exception e) {
        model.addAttribute("message", "Ошибка при предварительном просмотре отчета!");
        return "errorPage";

        Тестирующий код для функционального требования FR10-2 представлен ниже.

@Test
public void testPreviewReport() throws Exception {
        Long reportId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/previewReport/{reportId}", reportId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("previewReport"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("report"));
        }
