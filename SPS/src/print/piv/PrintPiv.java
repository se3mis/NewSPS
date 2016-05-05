package print.piv;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class PrintPiv implements Printable {
	private String pivNo;
	private String pivSeqNo;
	private String payingBankCode;
	private String payingBranchCode;
	private String cebBranch;
	private String costCenterNo;
	private String date;
	
	public String getPivNo() {
		return pivNo;
	}
	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}
	public String getPivSeqNo() {
		return pivSeqNo;
	}
	public void setPivSeqNo(String pivSeqNo) {
		this.pivSeqNo = pivSeqNo;
	}
	public String getPayingBankCode() {
		return payingBankCode;
	}
	public void setPayingBankCode(String payingBankCode) {
		this.payingBankCode = payingBankCode;
	}
	public String getPayingBranchCode() {
		return payingBranchCode;
	}
	public void setPayingBranchCode(String payingBranchCode) {
		this.payingBranchCode = payingBranchCode;
	}
	public String getCebBranch() {
		return cebBranch;
	}
	public void setCebBranch(String cebBranch) {
		this.cebBranch = cebBranch;
	}
	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public void print()
	{
		
	}
	@Override
	public int print(Graphics g, PageFormat format, int pageIndex)
			throws PrinterException {
		/* We'll assume that Jav2D is available.
		 */
		Graphics2D g2d = (Graphics2D) g;
		/* Move the origin from the corner of the Paper to the corner
		 * of the imageable area.
		 */
		g2d.translate(format.getImageableX(), format.getImageableY());
		/* Set the text color.
		 */
		g2d.setPaint(Color.black);
		/* Use a LineBreakMeasurer instance to break our text into
		 * lines that fit the imageable area of the page.
		 */
		Point2D.Float pen = new Point2D.Float();
		 g2d.drawString ("x, x", 10, 10);
		 g2d.drawString("x, x", 300, 10);
		 g2d.drawString("x, x", 600, 10);
		 g2d.drawString("0, 400", 10, 400);
		 g2d.drawString("300, 400", 300, 400);
		 g2d.drawString("600, 400", 600, 400);
		 g2d.drawString("10, 700", 10, 600);
		 g2d.drawString("300, 700", 300, 600);
		 g2d.drawString("600, 700", 600, 600);
		
		return Printable.PAGE_EXISTS;
	}
}
