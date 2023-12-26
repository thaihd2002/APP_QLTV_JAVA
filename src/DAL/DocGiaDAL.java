package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DTO.DocGia;
import DTO.LoaisachDTO;

public class DocGiaDAL {
	public static ArrayList<DocGia> getdanhsachdocgia() {
		try {
			String sql = "select * from docgia where trangthai =1";
			Connection conn = DBConnect.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<DocGia> dsl = new ArrayList<>();
			while (rs.next()) {
				// System.out.println("Db connect");
				DocGia ke = new DocGia();
				ke.setMaDocGia(rs.getInt(1));
				ke.setDiachi(rs.getString(4));
				ke.setTenDocGia(rs.getString(2));
				ke.setGioiTinh(rs.getString(3));
				ke.setSdt(rs.getString(5));
				dsl.add(ke);
			}

			return dsl;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	// @SuppressWarnings("null")
	public static int themdocgia(DocGia ke) {
		int i = -1;
		String sql = "insert into docgia (tendocgia,gioitinh,diachi,sdt,trangthai) values(?,?,?,?,?)";

		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, ke.getTenDocGia());
			pstm.setString(2, ke.getGioiTinh());

			pstm.setString(3, ke.getDiachi());
			pstm.setString(4, ke.getSdt());
			pstm.setInt(5, 1);
			i = pstm.executeUpdate();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			// dreturn null;
		}
		return i;
	}

	public static int suadocgia(DocGia ke) {
		int i = -1;
		String sql = "update docgia set tendocgia = ?,diachi =? ,gioitinh = ?,sdt = ? where madocgia = ?";

		try {

			Connection conn = DBConnect.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, ke.getTenDocGia());
			
			pstm.setString(2, ke.getDiachi());
			pstm.setString(3, ke.getGioiTinh());
			pstm.setString(4, ke.getSdt());
			pstm.setInt(5, ke.getMaDocGia());
			// System.out.println(ke.getViTri());
			i = pstm.executeUpdate();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			// dreturn null;
		}

		return i;
	}

	public static int xoadocgia(DocGia ke) {
		int i = -1;
	//	String sql = "delete from docgia where madocgia = ?";
		String sql = "update docgia set trangthai = 0 where madocgia = ?" ;
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, ke.getMaDocGia());
			// System.out.println(ke.getViTri());
			i = pstm.executeUpdate();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			// dreturn null;
		}
		return i;
	}
	public List<DocGia> timdocgia(String ma) {
		String sql = "select * from docgia where (madocgia = ? or tendocgia like ?) and trangthai = 1 ";
		List<DocGia> lisdg = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ma);
			ps.setString(2,"%"+ ma+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				DocGia docgia = new DocGia();
				docgia.setMaDocGia(rs.getInt(1));
				docgia.setDiachi(rs.getString(4));
				docgia.setTenDocGia(rs.getString(2));
				docgia.setGioiTinh(rs.getString(3));
				docgia.setSdt(rs.getString(5));
				lisdg.add(docgia);
			}
			if (lisdg.isEmpty()) {
			    return null;
			}else
			return lisdg; 
		} catch (Exception e) {
			e.printStackTrace();
			return null ; 
		}
		
	}
}
