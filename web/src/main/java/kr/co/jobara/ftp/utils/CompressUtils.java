package kr.co.jobara.ftp.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompressUtils {
	public static final String TEMPPREFIX = "Temporary_";
	public static final String ZIPSUFFIX = "zip";
	
	public static File compressIntoZipTempFile(Path base) throws IOException {
		File saveFile = Files.createTempFile(TEMPPREFIX, ZIPSUFFIX).toFile();
		compressIntoZipFile(base, saveFile);
		return saveFile;
	}
	
	public static File compressIntoZipFile(Path base, File saveFile) throws IOException {
		try(
			FileOutputStream os = new FileOutputStream(saveFile);
		){
			compressIntoZip(base, new FileOutputStream(saveFile));
			return saveFile;
		}
	} 
	
	public static long compressIntoZip(Path base, OutputStream os) throws IOException {
		try(
			ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(os);
		){
			Files.walkFileTree(base, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					if(!base.equals(dir)) {
						String entryName = base.relativize(dir).toString();
						ArchiveEntry archiveEntry = zaos.createArchiveEntry(dir, entryName);
						zaos.putArchiveEntry(archiveEntry);
						zaos.closeArchiveEntry();
						log.info("압축 대상 폴더에 상대적인 경로(entry name) : {}", entryName);
					}
					return super.preVisitDirectory(dir, attrs);
				}
				
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					String entryName = base.equals(file)? file.getFileName().toString(): base.relativize(file).toString();
					ArchiveEntry archiveEntry = zaos.createArchiveEntry(file, entryName);
					zaos.putArchiveEntry(archiveEntry);
					Files.copy(file, zaos);
					log.info("현재 압축 파일의 entry name : {}", entryName);
					zaos.closeArchiveEntry();
					return super.visitFile(file, attrs);
				}
			});
			return zaos.getBytesWritten();
		}
	}

}
