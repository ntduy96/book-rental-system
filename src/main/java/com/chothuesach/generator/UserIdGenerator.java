package com.chothuesach.generator;

import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserIdGenerator implements IdentifierGenerator {

	private static final Log logger = LogFactory.getLog(UserIdGenerator.class);

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Connection conn = session.connection();
		String id = null;
		String maNguoiDung = new String(Hex.encode(KeyGenerators.secureRandom(12 / 2).generateKey()));
		PreparedStatement p;
		try {
			p = conn.prepareStatement("SELECT TEN_NGUOI_DUNG FROM NGUOI_DUNG WHERE MA_NGUOI_DUNG = ?");
			p.setString(1, maNguoiDung);
			ResultSet rs = p.executeQuery();
			if (!rs.next()) {
				if(logger.isDebugEnabled()) logger.debug("new user id is generated:" + maNguoiDung);
				return maNguoiDung;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(logger.isDebugEnabled()) logger.debug("new user id is generated:" + id);
		return id;
	}

}
