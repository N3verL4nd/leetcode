package db;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IbdTest {
    private RandomAccessFile file;
    /**
     * ibd文件
     */
    private String fileName = "/usr/local/var/mysql/sbtest/sbtest1.ibd";
    /**
     * ibd 总页数
     */
    private long pageCount = 0;

    /**
     * 页大小
     */
    private long pageSize = 1024 * 16;

    /**
     * 根页
     */
    int rootPage = 3;

    /**
     * File Header 大小
     */
    int fileHeaderSize = 38;

    /**
     * Page Header 大小
     */
    int pageHeaderSize = 56;

    /**
     * File Trailer 大小
     */
    int fileTrailerSize = 8;

    /**
     * 16 机制输出二进制数组
     *
     * @param data
     * @return
     */
    public String printHexBinary(byte[] data) {
        final char[] hexCode = "0123456789ABCDEF".toCharArray();
        StringBuilder r = new StringBuilder(data.length * 3);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
            r.append(" ");
        }
        return r.toString();
    }

    @Before
    public void before() throws Exception {
        file = new RandomAccessFile(fileName, "r");
        if (file.length() % pageSize != 0) {
            throw new RuntimeException("总页数不是整数");
        }
        pageCount = file.length() / pageSize;
    }

    @After
    public void after() {
        if (file != null) {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void show(int currentPage) throws IOException {
        byte[] fileHeader = new byte[fileHeaderSize];
        byte[] pageHeader = new byte[pageHeaderSize];
        byte[] fileTrailer = new byte[fileTrailerSize];
        byte[] pageDirectory = null;
        file.seek(currentPage * pageSize);
        file.read(fileHeader);
        byte[] pageType = new byte[2];
        pageType[0] = fileHeader[24];
        pageType[1] = fileHeader[25];
        System.out.println("pageType: " + printHexBinary(pageType));

        file.read(pageHeader);
        byte[] pageLevel = new byte[2];
        pageLevel[0] = pageHeader[26];
        pageLevel[1] = pageHeader[27];
        System.out.println("pageLevel: " + printHexBinary(pageLevel));


        System.out.println("fileHeader: " + printHexBinary(fileHeader));
        System.out.println("pageHeader: " + printHexBinary(pageHeader));

        int slots = 0;
        file.seek(currentPage * pageSize + fileHeaderSize);
        slots = file.readShort();
        System.out.println("PAGE_N_DIR_SLOTS: " + slots);
        pageDirectory = new byte[slots * 2];
        file.seek((currentPage + 1) * pageSize - 8 - slots * 2);
        file.read(pageDirectory);
        System.out.println("pageDirectory: " + printHexBinary(pageDirectory));

        List<Integer> offsets = new ArrayList<>();
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(pageDirectory));
        while (inputStream.available() > 0) {
            offsets.add((int) inputStream.readShort());
        }
        Collections.reverse(offsets);
        System.out.println(offsets);

        for (int i = 0; i < offsets.size(); i++) {
            if (i == 0 || i == offsets.size() - 1) {
                continue;
            }
            file.seek(currentPage * pageSize + offsets.get(i));
            byte[] primaryKey = new byte[4];
            byte[] pageNo = new byte[4];

            file.read(primaryKey);
            primaryKey[0] = 0;
            file.read(pageNo);

            System.out.println("primaryKey: " + new BigInteger(primaryKey).intValue() + " pageNo: " + new BigInteger(pageNo).intValue());
        }


        file.seek((currentPage + 1) * pageSize - 8);
        file.read(fileTrailer);
        System.out.println("fileTrailer: " + printHexBinary(fileTrailer));

    }

    @Test
    public void showPageLevel() throws IOException {
        file.seek(rootPage * pageSize + fileHeaderSize + 26);
        // B+树高为 pageLevel + 1
        System.out.println("pageLevel = " + file.readShort());
    }

    @Test
    public void showAllSlots() throws IOException {
        for (int page = rootPage; page < pageCount; page++) {
            file.seek(page * pageSize + fileHeaderSize);
            System.out.println("page: " + page + " PAGE_N_DIR_SLOTS: " + file.readShort());
        }
    }

    @Test
    public void testPageDirectory() throws IOException {
        // 查询主键为 3905000 的记录
        show(3);
        show(36877);
        show(53781);
    }
}
