package com.thinkhr.external.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkhr.external.api.db.entities.Company;
import com.thinkhr.external.api.db.entities.CustomFields;
import com.thinkhr.external.api.db.entities.User;
import com.thinkhr.external.api.model.FileImportResult;

/**
 * Utility class to keep all utilities required for Junits
 * 
 * @author Surabhi Bhawsar
 * @Since 2017-11-06
 *
 */
public class ApiTestDataUtil {

    public static final String API_BASE_PATH = "/v1/";
    public static final String COMPANY_API_BASE_PATH = "/v1/companies/";
    public static final String USER_API_BASE_PATH = "/v1/users/";
    public static final String COMPANY_API_REQUEST_PARAM_OFFSET = "offset";
    public static final String COMPANY_API_REQUEST_PARAM_LIMIT = "limit";
    public static final String COMPANY_API_REQUEST_PARAM_SORT = "sort";
    public static final String COMPANY_API_REQUEST_PARAM_SEARCH_SPEC = "searchSpec";

    /**
     * Convert object into Json String
     * 
     * @param object
     * @param kclass
     * @return
     * @throws JAXBException 
     * @throws PropertyException 
     */
    public static String getJsonString(Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }

    /**
     * Create a Company Model for given inputs
     * 
     * @param companyId
     * @param companyName
     * @param brokerId
     * @return
     */
    public static Company createCompany(Integer companyId, String companyName, String companyType, String displayName, Date companySince,
            String specialNotes, String custom1, String searchHelp, Integer brokerId) {
        Company company = new Company();
        if (companyId != null) {
            company.setCompanyId(companyId);
        }
        company.setCompanyName(companyName);
        company.setCompanyType(companyType);
        company.setDisplayName(displayName);
        company.setCompanySince(companySince);
        company.setSpecialNote(specialNotes);
        company.setCustom1(custom1);
        company.setSearchHelp(searchHelp);
        company.setBroker(brokerId);
        company.setIsActive(1);
        return company;
    }

    /**
     * Create a Company Model for given inputs
     * 
     * @param companyId
     * @param companyName
     * @param companyType
     * @param displayName
     * @param companySince
     * @param specialNotes
     * @return
     */
    public static Company createCompany(Integer companyId, String companyName, String companyType, String displayName, Date companySince,
            String specialNotes, String searchHelp) {
        Company company = new Company();
        if (companyId != null) {
            company.setCompanyId(companyId);
        }
        company.setCompanyName(companyName);
        company.setCompanyType(companyType);
        company.setDisplayName(displayName);
        company.setCompanySince(companySince);
        company.setSpecialNote(specialNotes);
        company.setSearchHelp(searchHelp);
        company.setIsActive(1);
        return company;
    }

    /**
     * Create a User entity for given inputs
     * 
     * @param contactId
     * @param firstName
     * @param lastName
     * @param searchHelp
     * @return
     */
    public static User createUser(Integer userId, String firstName, String lastName, String email, String userName, String companyName) {
        User user = new User();
        if (userId != null) {
            user.setUserId(userId);
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUserName(userName);
        user.setCompanyName(companyName);
        return user;
    }

    /**
     * 
     * @return
     */
    public static User createUser() {
        return createUser(1, "Surabhi", "Bhawsar", "surabhi.bhawsar@pepcus.com", "sbhawsar", "Pepcus");
    }

    /**
     * Creates a user response object
     * 
     * @param user
     * @param httpStatus
     * @return
     */
    public static ResponseEntity<User> createUserResponseEntity(User user, HttpStatus httpStatus) {
        return new ResponseEntity<User>(user, httpStatus);
    }

    /**
     * Creates a company response object
     * 
     * @param company
     * @param httpStatus
     * @return
     */
    public static ResponseEntity<Company> createCompanyResponseEntity(Company company, HttpStatus httpStatus) {
        return new ResponseEntity<Company>(company, httpStatus);
    }

    /**
     * createCompanyIdResponseEntity
     * 
     * @param companyId
     * @param httpStatus
     * @return
     */
    public static ResponseEntity<Integer> createCompanyIdResponseEntity(Integer companyId, HttpStatus httpStatus) {
        return new ResponseEntity<Integer>(companyId, httpStatus);
    }

    /**
     * createContactIdResponseEntity
     * 
     * @param contactId
     * @param httpStatus
     * @return
     */
    public static ResponseEntity<Integer> createUserIdResponseEntity(Integer userId, HttpStatus httpStatus) {
        return new ResponseEntity<Integer>(userId, httpStatus);
    }

    /**
     * Create Model object for Company
     * 
     * @return
     */
    public static Company createCompany() {
        return createCompany(1, "Pepcus", "Software", "PEP", new Date(), "Special", "This is search help");
    }

    /**
     * Create List for Company objects
     * 
     * @return
     */
    public static List<Company> createCompanies() {
        List<Company> companies = new ArrayList<Company>();
        companies.add(createCompany(1, "Pepcus", "IT", "PEP", new Date(), "IT comp at Indore", "This is search help1"));
        companies.add(createCompany(2, "Google", "IT", "PEP", new Date(), "IT comp at Indore", "This is search help2"));
        companies.add(createCompany(3, "Facebook", "IT", "PEP", new Date(), "IT comp at Indore", "This is search help3"));
        companies.add(createCompany(4, "Suzuki", "IT", "PEP", new Date(), "IT comp at Indore", "This is search help4"));
        companies.add(createCompany(5, "General Motors", "IT", "PEP", new Date(), "IT comp at Indore", "This is search help5"));
        companies.add(createCompany(6, "L & T", "IT", "PEP", new Date(), "IT comp at Indore", "This is search help6"));
        companies.add(createCompany(7, "General Electric", "IT", "PEP", new Date(), "IT comp at Indore", "This is search help7"));
        companies.add(createCompany(8, "Oracle", "IT", "PEP", new Date(), "IT comp at Indore", "This is search help8"));
        companies.add(createCompany(9, "Microsoft", "IT", "PEP", new Date(), "IT comp at Indore", "This is search help9"));
        companies.add(createCompany(10, "Thinkhr", "IT", "PEP", new Date(), "IT comp at Indore", "This is search help10"));
        return companies;

    }

    /**
     * 
     * @return
     */
    public static List<CustomFields> createCustomFields() {
        List<CustomFields> list = new ArrayList<CustomFields>();
        list.add(createCustomField(1, 1, "1", "BUSINESS_ID"));
        list.add(createCustomField(1, 1, "3", "BRANCH_ID"));
        list.add(createCustomField(1, 1, "2", "CLIENT_ID"));
        list.add(createCustomField(1, 1, "4", "CLIENT_TYPE"));
        return list;
    }

    /**
     * 
     * @param id
     * @param companyId
     * @param customFieldColumnName
     * @param customFieldDisplayLabel
     * @return
     */
    public static CustomFields createCustomField(Integer id, Integer companyId, String customFieldColumnName,
            String customFieldDisplayLabel) {
        CustomFields customFields = new CustomFields();
        if (id != null) {
            customFields.setId(id);
        }
        customFields.setCompanyId(companyId);
        customFields.setCustomFieldColumnName(customFieldColumnName);
        customFields.setCustomFieldDisplayLabel(customFieldDisplayLabel);
        return customFields;
    }

    /**
     * Create List for User objects
     * 
     * @return
     */
    public static List<User> createUserList() {
        List<User> users = new ArrayList<User>();

        users.add(createUser(1, "Isha", "Khandelwal", "isha.khandelwal@gmail.com", "ishaa", "ThinkHR"));
        users.add(createUser(2, "Sharmila", "Tagore", "stagore@gmail.com", "stagore", "ASI"));
        users.add(createUser(3, "Surabhi", "Bhawsar", "sbhawsar@gmail.com", "sbhawsar", "Pepcus"));
        users.add(createUser(4, "Shubham", "Solanki", "ssolanki@gmail.com", "ssolanki", "Pepcus"));
        users.add(createUser(5, "Ajay", "Jain", "ajain@gmail.com", "ajain", "TCS"));
        users.add(createUser(6, "Sandeep", "Vishwakarma", "svishwakarma@gmail.com", "svishwakarma", "CIS"));
        users.add(createUser(7, "Sushil", "Mahajan", "smahajan@gmail.com", "smahajan", "ASI"));
        users.add(createUser(8, "Sumedha", "Wani", "swani@gmail.com", "swani", "InfoBeans"));
        users.add(createUser(9, "Mohit", "Jain", "mjain@gmail.com", "mjain", "Pepcus"));
        users.add(createUser(10, "Avi", "Jain", "ajain@gmail.com", "ajain", "Pepcus"));

        return users;
    }

    /**
     * Create List for CustomFields object
     * 
     * @return
     */
    public static List<CustomFields> createCustomFieldsList() {
        List<CustomFields> customFields = new ArrayList<CustomFields>();

        customFields.add(createCustomFields(2, 15472, "COMPANY", "1", "c1", "CORRELATION_ID", "0"));
        customFields.add(createCustomFields(3, 15472, "COMPANY", "2", "c2", "GROUP_ID", "0"));
        return customFields;
    }

    /**
     * 
     * @param id
     * @param companyId
     * @param customFieldType
     * @param customFieldColumnName
     * @param customFieldDisplayKey
     * @param customFieldDisplayLabel
     * @param isImportRequired
     * @return
     */
    public static CustomFields createCustomFields(Integer id, Integer companyId, String customFieldType, String customFieldColumnName,
            String customFieldDisplayKey, String customFieldDisplayLabel, String isImportRequired) {
        CustomFields customFields = new CustomFields();
        if (id != null) {
            customFields.setId(id);
        }
        customFields.setCompanyId(companyId);
        customFields.setCustomFieldType(customFieldType);
        customFields.setCustomFieldColumnName(customFieldColumnName);
        customFields.setCustomFieldDisplayKey(customFieldDisplayKey);
        customFields.setCustomFieldDisplayLabel(customFieldDisplayLabel);
        customFields.setIsImportRequired(isImportRequired);

        return customFields;
    }

    public static String getCsvRecord() {
        String record = "Adams Radio of Tallahassee LLC 123,Adams Radio of Tallahassee LLC,9522320916,3000 Olson Rd,,Tallahassee,FL,32308,Other,0,,5331058,56,11078286,Non PEO";
        return record;
    }

    public static String getCsvRecordForDuplicate() {
        String record = "Pepcus,Adams Radio of Tallahassee LLC,9522320916,3000 Olson Rd,,Tallahassee,FL,32308,Other,0,,5331058,56,11078286,Non PEO";
        return record;
    }

    public static String getCsvRecordForSpecialCase() {
        String record = "Paychex,Adams Radio of Tallahassee LLC,9522320916,3000 Olson Rd,,Tallahassee,FL,32308,Other,0,,5331058,56,11078286,Non PEO";
        return record;
    }

    /**
     * Create fileImportResult for no any failed records.
     * 
     * @return
     */
    public static FileImportResult createFileImportResultWithNoFailedRecords() {
        FileImportResult fileImportResult = new FileImportResult();
        fileImportResult.setTotalRecords(10);
        fileImportResult.setNumSuccessRecords(10);
        fileImportResult.setNumFailedRecords(0);
        return fileImportResult;
    }

    /**
     * Create fileImportResult for failed records.
     * 
     * @return
     */
    public static FileImportResult createFileImportResultWithFailedRecords() {
        FileImportResult fileImportResult = new FileImportResult();
        fileImportResult.setTotalRecords(10);
        fileImportResult.setNumSuccessRecords(7);
        fileImportResult.addFailedRecord(3, "", "Blank Record", "Skipped");
        fileImportResult.addFailedRecord(6,
                "Paychex,Adams Radio of Tallahassee LLC,9522320916,3000 Olson Rd,,Tallahassee,FL,32308,Other,0,,5331058,56,11078286,Non PEO",
                "Duplicate", "Skipped");
        fileImportResult.addFailedRecord(8, "Paychex,Adams Radio of Tallahassee LLC,", "Missing Fields in Record", "Record not added");
        return fileImportResult;
    }

    /**
     * Create input stream response entity for bulk data upload
     * 
     * @return
     * @throws FileNotFoundException
     */
    public static ResponseEntity<InputStreamResource> createInputStreamResponseEntityForBulkUpload() throws FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-disposition", "attachment;filename=companiesImportResult.csv");

        File responseFile = new File("src/test/resources/testdata/20_ResponseCSV.csv");

        ResponseEntity<InputStreamResource> responseEntity = ResponseEntity.ok().headers(headers).contentLength(responseFile.length())
                .contentType(MediaType.parseMediaType("text/csv")).body(new InputStreamResource(new FileInputStream(responseFile)));

        return responseEntity;
    }

    /**
     * Create multipartFile
     * 
     * @return
     * @throws IOException
     */
    public static MockMultipartFile createMockMultipartFile() throws IOException {
        File file = new File("src/test/resources/testdata/8_Example10Rec.csv");

        FileInputStream input = null;
        MockMultipartFile multipartFile = null;

        input = new FileInputStream(file);

        multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));

        return multipartFile;
    }

    /**
     * Create multipartFile for empty file.
     * 
     * @return
     * @throws IOException
     */
    public static MockMultipartFile createMockMultipartFile_EmptyFile() throws IOException {
        File file = new File("src/test/resources/testdata/1_EmptyCSV.csv");

        FileInputStream input = null;
        MockMultipartFile multipartFile = null;

        input = new FileInputStream(file);

        multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));

        return multipartFile;
    }

    /**
     * Create multipartFile for invalid file extension.
     * 
     * @return
     * @throws IOException
     */
    public static MockMultipartFile createMockMultipartFile_InvalidExtension() throws IOException {
        File file = new File("src/test/resources/testdata/2_InvalidExtension.xlsx");

        FileInputStream input = null;
        MockMultipartFile multipartFile = null;

        input = new FileInputStream(file);

        multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));

        return multipartFile;
    }

    /**
     * Create multipartFile for missing headers.
     * 
     * @return
     * @throws IOException
     */
    public static MockMultipartFile createMockMultipartFile_MissingHeaders() throws IOException {
        File file = new File("src/test/resources/testdata/3_MissingHeader.csv");

        FileInputStream input = null;
        MockMultipartFile multipartFile = null;

        input = new FileInputStream(file);

        multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));

        return multipartFile;
    }

    /**
     * Create multipartFile for exceeding max records.
     * 
     * @return
     * @throws IOException
     *
     */
    public static MockMultipartFile createMockMultipartFile_MaxRecordExceed() throws IOException {
        File file = new File("src/test/resources/testdata/5_10000Rec_ExceedMaxRec3500.csv");

        FileInputStream input = null;
        MockMultipartFile multipartFile = null;

        input = new FileInputStream(file);

        multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));

        return multipartFile;
    }

    /**
     * Get list for columns in company table
     * 
     * @return
     */
    public static List<String> getCompanyColumnList() {
        List<String> columnList = new ArrayList<String>();
        columnList.add("client_name");
        columnList.add("display_name");
        columnList.add("client_phone");
        columnList.add("industry");
        columnList.add("companySize");
        columnList.add("producer");
        columnList.add("custom1");
        columnList.add("custom2");
        columnList.add("custom3");
        columnList.add("custom4");
        return columnList;
    }

    /**
     * Get list for column values in company table
     * 
     * @return
     */
    public static List<Object> getCompanyColumnValuesList() {
        List<Object> columnValuesList = new ArrayList<Object>();
        columnValuesList.add("Pepcus Software Services");
        columnValuesList.add("Pepcus");
        columnValuesList.add("3457893455");
        columnValuesList.add("IT");
        columnValuesList.add("20");
        columnValuesList.add("AJain");
        columnValuesList.add("dummy_business");
        columnValuesList.add("dummy_branch");
        columnValuesList.add("dummy_client");
        columnValuesList.add("dummy_client_type");
        return columnValuesList;
    }

    /**
     * Get list for column values in location table
     * 
     * @return
     */
    public static List<Object> getLocationsColumnValuesList() {
        List<Object> locationValuesList = new ArrayList<Object>();
        locationValuesList.add("Eastern County");
        locationValuesList.add("dummy_address");
        locationValuesList.add("Texas City");
        locationValuesList.add("TX");
        locationValuesList.add("452001");
        return locationValuesList;
    }

    /**
     * Get list for columns in location table
     * 
     * @return
     */
    public static List<String> getLocationColumnList() {
        List<String> columnList = new ArrayList<String>();
        columnList.add("address");
        columnList.add("address2");
        columnList.add("city");
        columnList.add("state");
        columnList.add("zip");
        return columnList;
    }

    /**
     * Get query for inserting into company
     * 
     * @return
     */
    public static String testQueryForCompany() {
        return "INSERT INTO clients(client_name,display_name,client_phone,industry,companySize,producer,custom1,custom2,custom3,custom4,"
                + "search_help,client_type,special_note,client_since,t1_is_active) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
    }

    /**
     * Get query for inserting into locations
     * 
     * @return
     */
    public static String testQueryForLocation() {
        return "INSERT INTO locations(address,address2,city,state,zip,client_id) VALUES(?,?,?,?,?,?) ";
    }

    /**
     * Get all the headers in csv .
     * 
     * @return
     */
    public static String[] getAllHeadersForCompany() {
        String[] requiredHeaders = { "CLIENT_NAME", "DISPLAY_NAME", "PHONE", "ADDRESS", "ADDRESS2", "CITY", "STATE", "ZIP", "INDUSTRY",
                "COMPANY_SIZE", "PRODUCER", "BUSINESS_ID", "BRANCH_ID", "CLIENT_ID", "CLIENT_TYPE" };
        return requiredHeaders;
    }

    /**
     * Get all the headers in csv having some extra headers for testing.
     * 
     * @return
     */
    public static String[] getAllHeadersForCompanyWithExtraHeaders() {
        String[] requiredHeaders = { "CLIENT_NAME", "DISPLAY_NAME", "PHONE", "ADDRESS", "ADDRESS2", "CITY", "STATE", "ZIP", "INDUSTRY",
                "COMPANY_SIZE", "PRODUCER", "BUSINESS_ID", "BRANCH_ID", "CLIENT_ID", "CLIENT_TYPE", "COMPANY_TYPE", "BUSINESS_TYPE" };
        return requiredHeaders;
    }

    /**
     * Get headers available in csv for company.
     * 
     * @return
     */
    public static String[] getAvailableHeadersForCompany() {
        String[] requiredHeaders = { "CLIENT_NAME", "DISPLAY_NAME", "PHONE", "ADDRESS", "ADDRESS2", "CITY", "STATE", "ZIP" };
        return requiredHeaders;
    }

    /**
     * Get columns which are mapped to headers in csv.
     * 
     * @return
     */
    public static Map<String, String> getColumnsToHeadersMap() {
        Map<String, String> columnToHeaderMap = new HashMap<String, String>();
        columnToHeaderMap.put("companyName", "CLIENT_NAME");
        columnToHeaderMap.put("displayName", "DISPLAY_NAME");
        columnToHeaderMap.put("companyPhone", "PHONE");
        columnToHeaderMap.put("industry", "INDUSTRY");
        columnToHeaderMap.put("companySize", "COMPANY_SIZE");
        columnToHeaderMap.put("producer", "PRODUCER");
        return columnToHeaderMap;
    }

    /**
     * Get all the columns which are mapped to headers in csv.
     * 
     * @return
     */
    public static Map<String, String> getAllColumnsToHeadersMap() {
        Map<String, String> columnToHeaderMap = new HashMap<String, String>();
        columnToHeaderMap.put("companyName", "CLIENT_NAME");
        columnToHeaderMap.put("displayName", "DISPLAY_NAME");
        columnToHeaderMap.put("companyPhone", "PHONE");
        columnToHeaderMap.put("industry", "INDUSTRY");
        columnToHeaderMap.put("companySize", "COMPANY_SIZE");
        columnToHeaderMap.put("producer", "PRODUCER");
        columnToHeaderMap.put("custom1", "BUSINESS_ID");
        columnToHeaderMap.put("custom2", "BRANCH_ID");
        columnToHeaderMap.put("custom3", "CLIENT_ID");
        columnToHeaderMap.put("custom4", "CLIENT_TYPE");
        return columnToHeaderMap;
    }

    /**
    * Get custom headers for company
    * 
    * @return
    */
    public static Set<String> getCustomHeadersForCompany() {
        Set<String> customHeaders = new HashSet<String>();
        customHeaders.add("BUSINESS_ID");
        customHeaders.add("BRANCH_ID");
        customHeaders.add("CLIENT_ID");
        customHeaders.add("CLIENT_TYPE");
        return customHeaders;
    }

    /**
     * Get extra custom headers for company for testing.
     * 
     * @return
     */
    public static Set<String> getExtraCustomHeadersForCompany() {
        Set<String> customHeaders = new HashSet<String>();
        customHeaders.add("BUSINESS_ID");
        customHeaders.add("BRANCH_ID");
        customHeaders.add("CLIENT_ID");
        customHeaders.add("CLIENT_TYPE");
        customHeaders.add("COMPANY_TYPE");
        customHeaders.add("BUSINESS_TYPE");
        return customHeaders;
    }

    /**
     * Get location columns to header map
     * @return
     */
    public static Map<String, String> getLocationColumnsToHeadersMap() {
        Map<String, String> columnToHeaderMap = new HashMap<String, String>();
        columnToHeaderMap.put("address", "ADDRESS");
        columnToHeaderMap.put("address2", "ADDRESS2");
        columnToHeaderMap.put("city", "CITY");
        columnToHeaderMap.put("state", "STATE");
        columnToHeaderMap.put("zip", "ZIP");
        return columnToHeaderMap;
    }

    /**
     * Get csv headers to Index map
     * @return
     */
    public static Map<String, Integer> getHeaderIndexMap() {
        Map<String, Integer> headerIndexMap = new HashMap<String, Integer>();
        headerIndexMap.put("CLIENT_NAME", 0);
        headerIndexMap.put("DISPLAY_NAME", 1);
        headerIndexMap.put("PHONE", 2);
        headerIndexMap.put("INDUSTRY", 3);
        headerIndexMap.put("COMPANY_SIZE", 4);
        headerIndexMap.put("PRODUCER", 5);
        return headerIndexMap;
    }

    /**
     * Get a line of csv record
     * @return
     */
    public static String getFileRecord() {
        return "Pepcus Software Services, pepcus, 9213234567, IT, 20, Ajay Jain, , , , , , 12, , ";
    }

    /**
     * Get file contents with custom fields.
     * 
     * @return
     */
    public static String getFileRecordWithCustomFields() {
        return "Pepcus Software Services, pepcus, 9213234567, IT, 20, Ajay Jain";
    }

    /**
     * Create list of csv records
     * @return
     */
    public static List<String> getCsvRecords() {
        List<String> records = new ArrayList<String>();

        records.add(
                "CLIENT_NAME,DISPLAY_NAME,PHONE,ADDRESS,ADDRESS2,CITY,STATE,ZIP,INDUSTRY,COMPANY_SIZE,PRODUCER,BUSINESS_ID,BRANCH_ID,CLIENT_ID,CLIENT_TYPE");

        records.add(
                "10 Monroe St Inc,10 Monroe St Inc,7166994474,10 Monroe St,,Ellicottville,NY,14731,Other,0,,5330663,45,14073326,Non PEO");

        records.add(
                "8 1-2 Center Street LLC,8 1-2 Center Street LLC,5857040052,8 Center Street,,Geneseo,NY,14454,Other,0,,5330176,17,15056542,Non PEO");

        records.add(
                "A & L Windows Installation Corp,A & L Windows Installation Corp,5165081137,534 Webster Ave,,Uniondale,NY,11553,Other,0,,5330985,40,17105215,Non PEO");

        return records;
    }

    /**
     * Create List of blank csv records
     * @return
     */
    public static List<String> getBlankCsvRecords() {
        List<String> records = new ArrayList<String>();

        records.add(
                "CLIENT_NAME,DISPLAY_NAME,PHONE,ADDRESS,ADDRESS2,CITY,STATE,ZIP,INDUSTRY,COMPANY_SIZE,PRODUCER,BUSINESS_ID,BRANCH_ID,CLIENT_ID,CLIENT_TYPE");

        records.add(",,,,,,,,,,,,,,");

        records.add("");

        records.add("");

        return records;
    }

}
