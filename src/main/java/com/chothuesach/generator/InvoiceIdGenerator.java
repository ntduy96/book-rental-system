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

public class InvoiceIdGenerator implements IdentifierGenerator {

	private static final Log logger = LogFactory.getLog(UserIdGenerator.class);

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Connection conn = session.connection();
		String id = null;
		String maHoaDon = new String(Hex.encode(KeyGenerators.secureRandom(16 / 2).generateKey()));
		PreparedStatement p;
		try {
			p = conn.prepareStatement("SELECT MA_HOA_DON FROM HOA_DON WHERE MA_HOA_DON = ?");
			p.setString(1, maHoaDon);
			ResultSet rs = p.executeQuery();
			if (!rs.next()) {
				if (logger.isDebugEnabled())
					logger.debug("new invoice id is generated:" + maHoaDon);
				return maHoaDon;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (logger.isDebugEnabled())
			logger.debug("new invoice id is generated:" + id);
		return id;
	}
	
}
