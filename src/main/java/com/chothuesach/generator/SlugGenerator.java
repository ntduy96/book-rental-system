package com.chothuesach.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.chothuesach.helper.Slugify;
import com.chothuesach.model.Sach;

public class SlugGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Connection conn = session.connection();
		Sach sach = (Sach) object;
		String slug = Slugify.toSlug(sach.getTenSach());
		PreparedStatement p;
		try {
			p = conn.prepareStatement("SELECT SLUG FROM SACH WHERE SLUG = ?");
			p.setString(1, slug);
			ResultSet rs = p.executeQuery();
			if (!rs.next()) {
				return slug;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return slug;
	}

}
