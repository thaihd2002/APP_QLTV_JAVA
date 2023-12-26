package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.KeSachDTO;
import DTO.NhaXuatBan;
import DTO.KeSachDTO;

public class NhaXuatBanDAL {
	public static ArrayList<NhaXuatBan> getdanhnxb() {
		try {
			String sql = "select * from nhaxuatban where trangthai = 1";
			Connection conn = DBConnect.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<NhaXuatBan> dsl = new ArrayList<NhaXuatBan>();
			while (rs.next()) {

				NhaXuatBan nxb = new NhaXuatBan();
				nxb.setMaNXB(rs.getInt(1));
				nxb.setTenNXB(rs.getString(2));
				nxb.setDiaChi(rs.getString(3));
				nxb.setSdt(rs.getString(4));
				dsl.add(nxb);
			}

			return dsl;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	// @SuppressWarnings("null")
	public static int themnxb(NhaXuatBan ke) {
		int i = -1;
		String sql = "insert into nhaxuatban (tennxb,diachi,sdt,trangthai) values(?,?,?,?)";

		try {

			Connection conn = DBConnect.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, ke.getTenNXB());
			pstm.setString(2, ke.getDiaChi());
			pstm.setString(3, ke.getSdt());
			pstm.setInt(4, 1);
			i = pstm.executeUpdate();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			// dreturn null;
		}

		return i;
	}

	public static int suanxb(NhaXuatBan ke) {
		int i = -1;
		String sql = "update nhaxuatban set tennxb = ? , diachi = ?, sdt = ?  where manxb = ?";

		try {

			Connection conn = DBConnect.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, ke.getTenNXB());
			pstm.setString(2, ke.getDiaChi());
			pstm.setString(3, ke.getSdt());
			pstm.setInt(4, ke.getMaNXB());

			// System.out.println(ke.getViTri());
			i = pstm.executeUpdate();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			// dreturn null;
		}

		return i;
	}

	public static int xoanxb(NhaXuatBan ke) {
		int i = -1;
		String sql = "update nhaxuatban set trangthai = 0  where manxb = ?";

		try {

			Connection conn = DBConnect.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, ke.getMaNXB());
			// System.out.println(ke.getViTri());
			i = pstm.executeUpdate();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			// dreturn null;
		}

		return i;
	}

	public List<NhaXuatBan> timnxb(String ma) {
		String sql = "select * from nhaxuatban where manxb = ? or tennxb like ?";
		List<NhaXuatBan> lstnxb = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, ma);
			pstm.setString(2, "%"+ma+"%");
			// System.out.println(ke.getViTri());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				NhaXuatBan nxb = new NhaXuatBan();
				nxb.setMaNXB(rs.getInt(1));
				nxb.setTenNXB(rs.getString(2));
				nxb.setDiaChi(rs.getString(3));
				nxb.setSdt(rs.getString(4));
				lstnxb.add(nxb);
			}
			conn.close();
			if (lstnxb.isEmpty()) {
			    return null;
			} else {
			    return lstnxb;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
