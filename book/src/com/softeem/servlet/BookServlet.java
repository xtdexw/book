package com.softeem.servlet;

import com.softeem.bean.Tbook;
import com.softeem.service.BookService;
import com.softeem.service.impl.BookServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.Page;
import com.softeem.utils.WebUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/BookServlet")
public class BookServlet extends BaseServlet {

    protected void searchPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        String name = request.getParameter("name") == null ? "" : request.getParameter("name");
        String author = request.getParameter("author") == null ? "" : request.getParameter("author");
        Integer min = WebUtils.parseInt(request.getParameter("min"), 0);
        Integer max = WebUtils.parseInt(request.getParameter("max"), 0);
        request.setAttribute("name",name);
        request.setAttribute("author",author);
        request.setAttribute("min",request.getParameter("min"));
        request.setAttribute("max",request.getParameter("max"));
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);//取不到值默认显示第一页
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);//取不到值默认每页显示4条数据
        try {
            Page<Tbook> page = bookService.page(pageNo, pageSize,name,author,new BigDecimal(min),new BigDecimal(max));
            page.setUrl("BookServlet?action=searchPage&name="+name+"&author="+author+"&min="+(min==0?"":min)+"&max="+(max==0?"":max));
            request.setAttribute("page",page);
            request.getRequestDispatcher("/home.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);//取不到值默认显示第一页
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);//取不到值默认每页显示4条数据
        try {
            Page<Tbook> page = bookService.page(pageNo, pageSize);
            page.setUrl("BookServlet?action=page");
            request.setAttribute("page",page);
            request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        Integer id = WebUtils.parseInt(request.getParameter("id"),0);
        try {
            String imgPath = request.getParameter("imgPath");
            WebUtils.deleteFile(imgPath);
            bookService.deletebookById(id);
            Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
            response.sendRedirect("BookServlet?action=page&pageNo="+pageNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void updateBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Tbook book = new Tbook();
        BookService bookService = new BookServiceImpl();
        Integer pageNo = null;
        if (ServletFileUpload.isMultipartContent(request)) {
            //创建FileItemFactory 工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            // 创建用于解析上传数据的工具类ServletFileUpload 类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                // 解析上传的数据，得到每一个表单项FileItem
                List<FileItem> list = servletFileUpload.parseRequest(request);
                //循环这6段数据并处理它们
                for (FileItem fileItem : list) {
                    //判断那些是普通表单项,还是上传的文件类型
                    if (fileItem.isFormField()) {
                        //处理普通表单项
//                        System.out.println(fileItem.getFieldName() + " = " + MyUtils.parseString(fileItem.getString()));
                        ///处理乱码问题
                        if ("name".equals(fileItem.getFieldName())) {
                            book.setName(fileItem.getString("utf-8"));   //图书名字
                        } else if ("author".equals(fileItem.getFieldName())) {
                            book.setAuthor(fileItem.getString("utf-8")); //图书作者
                        } else if ("price".equals(fileItem.getFieldName())) {
                            book.setPrice(new BigDecimal(fileItem.getString())); //图书价格
                        } else if ("sales".equals(fileItem.getFieldName())) {
                            book.setSales(Integer.valueOf(fileItem.getString())); //图书销售
                        } else if ("stock".equals(fileItem.getFieldName())) {
                            book.setStock(Integer.valueOf(fileItem.getString())); //图书销售
                        } else if ("pageNo".equals(fileItem.getFieldName())){
                            pageNo = Integer.valueOf(fileItem.getString());
                        }
                    } else {
                        String id = request.getParameter("id");
                        Tbook tbook = bookService.queryBookById(Integer.valueOf(id));
                        String imgPath = tbook.getImgPath();
                        book.setId(Integer.valueOf(id));

                        //处理文件类型(文件上传)
                        String filename = fileItem.getName();//获取文件名
                        //文件名 = 123.jpg       suffix = .jpg

                        if (!filename.equals("")) {
                            File oldfile = new File("D:", tbook.getImgPath());
                            boolean delete = oldfile.delete();
                            System.out.println("是否删除成功" + delete);

                            String suffix = filename.substring(filename.lastIndexOf("."));
                            //通过时间毫秒 + 后缀 = 新文件名
                            String newfilename = System.currentTimeMillis() + suffix;

                            //得到了工程路径
//                        ServletContext application = this.getServletContext();
//                        String realPath = application.getRealPath("/");

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String format = simpleDateFormat.format(new Date());
                            File newfile = new File("d:/bookimg/" + format + "/");
                            if (!newfile.exists()) {
                                newfile.mkdirs();//创建目录
                            }

                            fileItem.write(new File(newfile, newfilename));
                            book.setImgPath("/bookimg/" + format + "/" + newfilename);
                        } else {
                            //没有修改图片
                            book.setImgPath(imgPath);
                        }
                    }
                }
                bookService.updateBook(book);
//                Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
                response.sendRedirect("BookServlet?action=searchPage&pageNo="+pageNo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            out.println("不是多段数据..无法上传文件!");
        }
    }

    protected void queryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        String id = request.getParameter("id");
        try {
            Tbook tbook = bookService.queryBookById(Integer.valueOf(id));
            String pageNo = request.getParameter("pageNo");
            request.setAttribute("pageNo",pageNo);
            request.setAttribute("book", tbook);
            request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        try {
            List<Tbook> tbookList = bookService.queryBooks();
            request.setAttribute("tbookList", tbookList);
            request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Tbook book = new Tbook();
        BookService bookService = new BookServiceImpl();
        if (ServletFileUpload.isMultipartContent(request)) {
            //创建FileItemFactory 工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            // 创建用于解析上传数据的工具类ServletFileUpload 类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                // 解析上传的数据，得到每一个表单项FileItem
                List<FileItem> list = servletFileUpload.parseRequest(request);
                //循环这6段数据并处理它们
                for (FileItem fileItem : list) {
                    //判断那些是普通表单项,还是上传的文件类型
                    if (fileItem.isFormField()) {
                        //处理普通表单项
//                        System.out.println(fileItem.getFieldName() + " = " + MyUtils.parseString(fileItem.getString()));
                        ///处理乱码问题
                        if ("name".equals(fileItem.getFieldName())) {
                            book.setName(fileItem.getString("utf-8"));   //图书名字
                        } else if ("author".equals(fileItem.getFieldName())) {
                            book.setAuthor(fileItem.getString("utf-8")); //图书作者
                        } else if ("price".equals(fileItem.getFieldName())) {
                            book.setPrice(new BigDecimal(fileItem.getString())); //图书价格
                        } else if ("sales".equals(fileItem.getFieldName())) {
                            book.setSales(Integer.valueOf(fileItem.getString())); //图书销售
                        } else if ("stock".equals(fileItem.getFieldName())) {
                            book.setStock(Integer.valueOf(fileItem.getString())); //图书销售
                        }
                    } else {
                        //处理文件类型(文件上传)
                        String filename = fileItem.getName();//获取文件名
                        //文件名 = 123.jpg       suffix = .jpg
                        String suffix = filename.substring(filename.lastIndexOf("."));
                        //通过时间毫秒 + 后缀 = 新文件名
                        String newfilename = System.currentTimeMillis() + suffix;

                        //得到了工程路径
//                        ServletContext application = this.getServletContext();
//                        String realPath = application.getRealPath("/");

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
                        String format = simpleDateFormat.format(new Date());
                        File file = new File("d:/bookimg/" + format + "/");
                        if (!file.exists()) {
                            file.mkdirs();//创建目录
                        }
//                        System.out.println(file.getAbsolutePath());
                        fileItem.write(new File(file, newfilename));
                        book.setImgPath("/bookimg/" + format + "/" + newfilename);
                    }
                }
                bookService.addBook(book);
                response.sendRedirect("BookServlet?action=page");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            out.println("不是多段数据..无法上传文件!");
        }
    }

}
