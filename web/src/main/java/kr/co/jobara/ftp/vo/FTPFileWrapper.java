package kr.co.jobara.ftp.vo;

import org.apache.commons.net.ftp.FTPFile;

import kr.co.jobara.vo.fancytree.FancyTreeNode;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude="realRes")
public class FTPFileWrapper implements FancyTreeNode{
	private transient FTPFile realRes;
	
	public FTPFileWrapper(FTPFile realRes) {
		super();
		this.realRes = realRes;
	}

	@Override
	public String getTitle() {
		return realRes.getName();
	}

	@Override
	public String getKey() {
		return realRes.getName();
	}

	@Override
	public boolean isFolder() {
		return realRes.isDirectory();
	}

	@Override
	public boolean isLazy() {
		return realRes.isDirectory();
	}
	
}
