package com.chothuesach.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Connection conn = session.connection();
        String id = null;
        String maTacGia = new String(Hex.encode(KeyGenerators.secureRandom(12 / 2).generateKey()));
        PreparedStatement p;
        try {
            p = conn.prepareStatement("SELECT MA_TAC_GIA FROM SACH WHERE MA_TAC_GIA = ?");
            p.setString(1, maTacGia);
            ResultSet rs = p.executeQuery();
            if (!rs.next()) {
                return maTacGia;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

}
