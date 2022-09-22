package com.hogrider.learnjava.service;

import javax.sql.DataSource;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private MailService mailService;

//    UserService 继续注入 DataSource
    private DataSource dataSource;

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    public User login(String email, String password) {
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement ps = conn.prepareStatement("select * from user where email = ?");
            ps.setObject(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String dbPassword = rs.getString("password");
                    if (dbPassword.equals(shaEncode(password))) {
                        Long dbId = rs.getLong("id");
                        String dbName = rs.getString("name");
                        return new User(dbId, email, dbPassword, dbName);
                    }
                } else {
                    throw new RuntimeException("email not exist.");
                }
            }
            throw new RuntimeException("login password error.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User register(String email, String password, String name) throws SQLException {
        try (Connection conn = this.dataSource.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("select * from user where email = ?")) {
                ps.setObject(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new RuntimeException("email exist.");
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO user (email, password, name) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setObject(1, email);
                ps.setObject(2, shaEncode(password));
                ps.setObject(3, name);
                int n = ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        long dbId = rs.getLong(1);
                        User user = new User(dbId, email, password, name);
                        mailService.sendRegistrationMail(user);
                        return user;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;}

    public DataSource getDataSource() {
        return dataSource;
    }


    public  String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
