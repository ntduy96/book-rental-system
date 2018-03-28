package com.chothuesach.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class BookIdGenerator implements IdentifierGenerator {

	private static final Log logger = LogFactory.getLog(UserIdGenerator.class);

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Connection conn = session.connection();
		String id = null;
		String maSach = new String(Hex.encode(KeyGenerators.secureRandom(12 / 2).generateKey()));
		PreparedStatement p;
		try {
			p = conn.prepareStatement("SELECT TEN_SACH FROM SACH WHERE MA_SACH = ?");
			p.setString(1, maSach);
			ResultSet rs = p.executeQuery();
			if (!rs.next()) {
				if (logger.isDebugEnabled())
					logger.debug("new book id is generated:" + maSach);
				return maSach;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (logger.isDebugEnabled())
			logger.debug("new book id is generated:" + id);
		return id;
	}

}
