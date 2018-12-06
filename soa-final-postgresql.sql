--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1
-- Dumped by pg_dump version 11.1

-- Started on 2018-12-05 16:42:06

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 204 (class 1259 OID 17033)
-- Name: ACTIVITYID_AI; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public."ACTIVITYID_AI"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 198 (class 1259 OID 16954)
-- Name: notification; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.notification (
    "NOTIFICATIONID" integer NOT NULL,
    "TITLE" character varying(255) NOT NULL,
    "CONTENT" text NOT NULL,
    "ACTIVITYID" integer,
    "CREATED_AT" timestamp without time zone NOT NULL
);


--
-- TOC entry 197 (class 1259 OID 16952)
-- Name: NOTIFICATIONID_seqde16fa6991404131ab676ff9d28fd353; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public."NOTIFICATIONID_seqde16fa6991404131ab676ff9d28fd353"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2880 (class 0 OID 0)
-- Dependencies: 197
-- Name: NOTIFICATIONID_seqde16fa6991404131ab676ff9d28fd353; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public."NOTIFICATIONID_seqde16fa6991404131ab676ff9d28fd353" OWNED BY public.notification."NOTIFICATIONID";


--
-- TOC entry 205 (class 1259 OID 17040)
-- Name: SEMESTERID_SEQ; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public."SEMESTERID_SEQ"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 196 (class 1259 OID 16934)
-- Name: account; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.account (
    "USERNAME" character varying(20) NOT NULL,
    "PASSWORD" character varying(50) NOT NULL,
    "EMAIL" character varying(100) NOT NULL,
    "ROLE" character varying(7) NOT NULL
);


--
-- TOC entry 201 (class 1259 OID 16999)
-- Name: activity; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.activity (
    "ACTIVITYID" integer DEFAULT nextval('public."ACTIVITYID_AI"'::regclass) NOT NULL,
    "ACTIVITYCONTENT" text NOT NULL,
    "SEMESTERID" integer NOT NULL,
    "SCORE" integer NOT NULL,
    "NUMBEROFJOIN" integer DEFAULT 0 NOT NULL,
    "LIMITOFJOIN" integer DEFAULT 20 NOT NULL,
    "DATE" timestamp(4) without time zone DEFAULT now() NOT NULL,
    "STATUS" character varying(10) DEFAULT 'open'::character varying NOT NULL,
    "PLACE" character varying(255) DEFAULT 'Đang cập nhật'::character varying NOT NULL
);


--
-- TOC entry 202 (class 1259 OID 17009)
-- Name: semester; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.semester (
    "SEMESTERID" integer DEFAULT nextval('public."SEMESTERID_SEQ"'::regclass) NOT NULL,
    "SEMESTER" character varying(20) NOT NULL,
    "YEARS" character varying(20) NOT NULL
);


--
-- TOC entry 199 (class 1259 OID 16963)
-- Name: student; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.student (
    "STUDENTID" character varying(20) NOT NULL,
    "STUDENTNAME" text NOT NULL,
    "EMAIL" character varying(100) NOT NULL,
    "BIRTHDAY" timestamp without time zone NOT NULL,
    "GENDER" character varying(5) NOT NULL,
    "CLASS" character varying(20) NOT NULL,
    "DEPARTMENT" character varying(50) NOT NULL
);


--
-- TOC entry 200 (class 1259 OID 16971)
-- Name: student_activity; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.student_activity (
    "STUDENTID" character varying(20) NOT NULL,
    "ACTIVITYID" integer NOT NULL
);


--
-- TOC entry 203 (class 1259 OID 17018)
-- Name: student_semester; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.student_semester (
    "STUDENTID" character varying(20) NOT NULL,
    "SEMESTERID" integer NOT NULL,
    "TOTALSCORE" integer NOT NULL
);


--
-- TOC entry 2716 (class 2604 OID 16985)
-- Name: notification NOTIFICATIONID; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.notification ALTER COLUMN "NOTIFICATIONID" SET DEFAULT nextval('public."NOTIFICATIONID_seqde16fa6991404131ab676ff9d28fd353"'::regclass);


--
-- TOC entry 2865 (class 0 OID 16934)
-- Dependencies: 196
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.account ("USERNAME", "PASSWORD", "EMAIL", "ROLE") VALUES ('cnnq123', '12345', 'caongocnhuquynh0603@gmail.com', 'student');
INSERT INTO public.account ("USERNAME", "PASSWORD", "EMAIL", "ROLE") VALUES ('dhphuc', '12345', 'dhp@it.tdt.edu.vn', 'admin');
INSERT INTO public.account ("USERNAME", "PASSWORD", "EMAIL", "ROLE") VALUES ('lvk', '12345', 'lvk@gmail.com', 'student');


--
-- TOC entry 2870 (class 0 OID 16999)
-- Dependencies: 201
-- Data for Name: activity; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.activity ("ACTIVITYID", "ACTIVITYCONTENT", "SEMESTERID", "SCORE", "NUMBEROFJOIN", "LIMITOFJOIN", "DATE", "STATUS", "PLACE") VALUES (1, 'Khai mạc bóng đá sinh viên 17/10/2015', 1, 5, 0, 20, '2018-12-05 00:00:00', 'close', 'Đang cập nhật');
INSERT INTO public.activity ("ACTIVITYID", "ACTIVITYCONTENT", "SEMESTERID", "SCORE", "NUMBEROFJOIN", "LIMITOFJOIN", "DATE", "STATUS", "PLACE") VALUES (2, 'Tham gia chương trình Run to Future', 2, 2, 0, 20, '2018-12-05 00:00:00', 'close', 'Đang cập nhật');
INSERT INTO public.activity ("ACTIVITYID", "ACTIVITYCONTENT", "SEMESTERID", "SCORE", "NUMBEROFJOIN", "LIMITOFJOIN", "DATE", "STATUS", "PLACE") VALUES (3, 'Tham gia trực tuyến cuộc thi ánh sáng thời đại', 3, 3, 0, 20, '2018-12-05 00:00:00', 'close', 'Đang cập nhật');
INSERT INTO public.activity ("ACTIVITYID", "ACTIVITYCONTENT", "SEMESTERID", "SCORE", "NUMBEROFJOIN", "LIMITOFJOIN", "DATE", "STATUS", "PLACE") VALUES (4, 'Tham gia chương trình hội diễn văn nghệ khoa CNTT này 15/11/2017', 4, 3, 0, 20, '2018-12-05 00:00:00', 'close', 'Đang cập nhật');
INSERT INTO public.activity ("ACTIVITYID", "ACTIVITYCONTENT", "SEMESTERID", "SCORE", "NUMBEROFJOIN", "LIMITOFJOIN", "DATE", "STATUS", "PLACE") VALUES (5, 'Tham dự buổi chuyên đề “Công dân toàn cầu – Bạn sẽ ở đâu trong thế kỷ 21”', 5, 3, 0, 20, '2018-12-05 00:00:00', 'close', 'Đang cập nhật');


--
-- TOC entry 2867 (class 0 OID 16954)
-- Dependencies: 198
-- Data for Name: notification; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.notification ("NOTIFICATIONID", "TITLE", "CONTENT", "ACTIVITYID", "CREATED_AT") VALUES (1, 'Đăng kí tham gia buổi Training kiến thức CNTT của công ty Nashtech', 'Thân chào các bạn sinh viên,

Nhằm hỗ trợ các bạn sinh viên năm 3,4 chuẩn bị tốt hơn trong việc tìm kiếm cơ hội việc làm sau khi ra trường, Công ty NashTech VietNam quyết định tổ chức một buổi đào tạo nhỏ dành riêng cho sinh viên trường Tôn ĐứcThắng để cập nhật kiến thức và chuẩn bị CV ứng tuyển. Nội dung và thời gian tổ chức sự kin theo lịch trình bên dưới:

Thời gian: Thứ 6 ngày 02/11/2018 từ 13h45 – 17h
Địa điểm: văn phòng Etown 4 của công ty NashTech, 364 Cộng Hòa Phường 13, Quận Tân Bình. ( Gửi xe tại cổng số 5 Etown,đường Ấp Bắc Phường 13, Quận Tân Bình )
Số lượng tham dự: 50 bạn (ưu tiên sinh viên năm 3,4 và các bạn đăng ký trước).
Sinh viên tự túc phương tiện di chuyển.
Link đăng ký: https://goo.gl/pZEfqk', 1, '2018-11-12 00:00:00');
INSERT INTO public.notification ("NOTIFICATIONID", "TITLE", "CONTENT", "ACTIVITYID", "CREATED_AT") VALUES (2, 'Đăng kí tham dự Seminar công ty Fujinet', 'Khoa CNTT thân mời các bạn sinh viên tham dự Seminar với chủ đề '' Xu hướng công nghệ và một số kỹ năng cần thiết để đáp ứng công việc'' :

- Thời gian: 09h30 - 11h30 Thứ năm ngày 02/11/2017.
- Địa điểm: Phòng họp C (Dự kiến)
- Trình bày: Thạc sĩ Tạ Ngọc Huy Đông – Trưởng Bộ phận lập trình tại Fujinet.
- Link đăng kí: https://goo.gl/forms/8ot7k3NkYKPOKxFH3
- Nội dung: 

Cloud Computing - Điện toán đám mây
Mobile & Wearable - Di động và Thiết bị đeo trên người
Big Data & IoT – Dữ liệu lớn và Vạn vật kết nối
Smart Machine – Máy móc thông minh
The inevitable (12 Technology Forces) - 12 luật kỹ thuật không thể tránh khỏi.
Bối cảnh ngành IT Nhật Bản – Cơ hội cho sinh viên Việt Nam
 Một số kỹ năng cần thiết để đáp ứng tốt công việc', 5, '2018-11-11 00:00:00');
INSERT INTO public.notification ("NOTIFICATIONID", "TITLE", "CONTENT", "ACTIVITYID", "CREATED_AT") VALUES (3, 'Thông báo đăng ký tham gia Mùa hè xanh năm 2016', '[Thông báo từ BCH Đoàn-Hội sinh viên Khoa CNTT]

Thân chào các bạn sinh viên,

Tiếp nối thành công chiến dịch Mùa hè xanh 2015, Mùa hè xanh 2016 với slogan ''Sinh viên CNTT- đi dân nhớ, ở dân thương, làm dân tin'' hứa hẹn sẽ mang đến một mùa hè sôi động nhưng tràn đầy tình yêu thương bên bạn bè. Sẽ là một cuộc hội ngộ của những chiến sĩ Mùa hè xanh các năm trước, sẽ là cuộc chào sân của các chiến sĩ Mùa hè xanh 2016 này. Cuộc hội ngộ của những tâm hồn trẻ sẽ dẫn dắt chúng ta đến với một cuộc dấn thân mà chỉ những con người trẻ thật trẻ, “trẻ” nơi tâm hồn và luôn tràn trề sinh lực mới đủ sức để kịp với nhịp đua của nắng hè.
CHIẾN DỊCH MÙA HÈ XANH 2016 – CHIẾN DỊCH TÌNH NGUYỆN LỚN NHẤT TRONG NĂM CỦA KHOA CÔNG NGHỆ THÔNG TIN đã chính thức phát động. Còn chần chờ gì nữa, cùng đăng kí tham gia nào các bạn ơi....

+ Thời gian đăng ký: từ ngày 15/06/2016 đến ngày 20/06/2016.
+ Thời gian diễn ra chiến dịch: từ ngày 10/07/2016 đến ngày 05/08/2016.
+ Địa điểm hoạt động: Phường Tân Thuận Đông, Quận 7, Tp.HCM
+ Link đăng kí: http://goo.gl/forms/kW2OYiIeQA0e652u1
 


*** LƯU Ý:
- Những bạn có nhu cầu đăng kí áo Mùa hè xanh sẽ đăng kí trực tiếp tại link đăng kí. Giá áo: 60.000 VNĐ/Áo.
- Toàn bộ chiến dịch sẽ có 16 hoạt động, các chiến sĩ tham gia tối thiểu 10 hoạt động sẽ được công nhận.
- Khi đã đăng lý tham gia chương trình, sinh viên bắt buộc phải tham gia, Khoa sẽ xử lý đối với những sinh viên đã đăng ký nhưng không tham gia, nếu có lý do bất khả kháng không tham gia được vui lòng viết đơn gửi về VP Khoa (C004) trước khi hoạt động diễn ra.
- Đây là link đăng ký tham gia toàn chiến dịch, khi đã chính thức trở thành chiến sĩ Mùa hè xanh 2016, các bạn sẽ được đăng kí tham gia từng hoạt động. Các bạn nhớ theo dõi mail thường xuyên nhé.
- Phương tiện di chuyển: xe máy/bus, tùy vào số lượng xe của chiến sĩ tham gia hoạt động đó thì sẽ có phương án thích hợp.', 4, '2018-11-11 00:00:00');
INSERT INTO public.notification ("NOTIFICATIONID", "TITLE", "CONTENT", "ACTIVITYID", "CREATED_AT") VALUES (4, 'Thông báo chạy bộ từ thiện Terry Fox Run 2017', 'Thân chào các bạn sinh viên,
Triển khai Cuộc chạy bộ từ thiện Terry Fox Run 2017.

Terry Fox Run là gì?
Terry Fox Run là sự kiện chạy bộ từ thiện thường niên được tổ chức nhằm gây quỹ nghiên cứu ung thư. Sự kiện này bắt đầu từ năm 1981 ở Canada, đặt theo tên Terry Fox, một vận động viên đã chạy xuyên Canada trong hành trình Marathon of Hope năm 1980 để gây quỹ và nâng nhận thức của cộng đồng về việc nghiên cứu chữa bệnh ung thư. Từ thành công với Terry Fox Run ở Canada, sự kiện được mở rộng ra trên toàn thế giới và nhận được sự ủng hộ của tất cả mọi người ở mọi châu lục. Terry Fox Run được tổ chức ở Việt Nam từ năm 1996 và từ đó đến nay luôn là một trong những sự kiện từ thiện quy mô nhất, thu hút ngày càng đông người tham gia.

Và, TERRY FOX RUN 2017!

-  Thời gian: 6h30, ngày 05.11 (Chủ nhật)
-  Địa điểm: Đường Tân Trào, Phú Mỹ Hưng, Quận 7. 
(Địa điểm tập trung và sơ đồ đường chạy đính kèm trong link đăng ký)
-  Trang phục: Áo Thể dục Trường đại học Tôn Đức Thắng.
-  Link đăng ký: http://bit.ly/2z8KafY

Mọi thắc mắc các bạn chúng ta liên hệ: (Bạn) Trương Chí Trung - Chủ tịch Hội Sinh viên Đại học Tôn Đức Thắng.
(Số điện thoại: 0914.088.221)
--
Chúc các bạn học tập và rèn luyện thật tốt!

Và đặc biệt, các bạn sinh viên có thể ủng hộ (hoàn toàn tự nguyện) cho chương trình bằng cách đăng ký mua áo (200.000đ/1 áo) của chương trình, tại văn phòng A004. (Hạn chót đăng ký mua áo: 17h30, ngày 03.11 (Thứ Sáu))', 3, '2018-11-12 00:00:00');
INSERT INTO public.notification ("NOTIFICATIONID", "TITLE", "CONTENT", "ACTIVITYID", "CREATED_AT") VALUES (5, 'Đăng ký tham gia đội biểu diễn Lễ khai mạc “Giải bóng đá sinh viên Thành phố Hồ Chí Minh 2016”', 'Chào các bạn sinh viên,

Hằng năm, Nhà Văn Hóa Sinh Viên Thành phố Hồ Chí Minh cùng Trường hợp tác tổ chức nhiều hoạt động cho sinh viên Trường nói riêng và sinh viên toàn thành nói chung. Phòng CTHSSV mời bạn đăng ký tham gia vào đội biểu diễn Lễ Khai mạc “Giải bóng đá sinh viên Thành phố Hồ Chí Minh 2016” với thông tin như sau: 

- Số lượng và yêu cầu

+ 250 bạn sinh viên nam cao tối thiểu 1m70

+ 120 bạn sinh viên nữ cao tối thiểu 1m60 

- Địa điểm: Sân vận động Trường Đại học Tôn Đức Thắng 

- Thời gian:

+ Tập luyện: 3 buổi từ 16h00 – 17h30 ngày 07, 09, 11/11/2016(gồm thứ 2, 4, 6)

+ Tổng duyệt: 15h00 ngày 12/11/2016 (thứ 7)

+ Diễn ra chính thức: 13h30 ngày 13/11/2016 (chủ nhật)

 - Sinh viên khi tham gia sẽ được cộng điểm 10 rèn luyện.

- Link đăng ký và hạn chót đăng ký vào ngày 20h ngày 06/11/2016:http://bit.ly/tuyendoibieudienkhaimacbongdasinhvientphcm2016', 2, '2018-11-05 00:00:00');
INSERT INTO public.notification ("NOTIFICATIONID", "TITLE", "CONTENT", "ACTIVITYID", "CREATED_AT") VALUES (6, 'Thông báo V/v đăng ký tham gia chương trình chạy bộ gây quỹ từ thiện “RUN TO FUTURE 2015”', 'Thân gửi các bạn sinh viên!

Chương trình chạy bộ tình nguyện gây quỹ từ thiện Run to Future 2015 – mang chủ đề “ Building to future” với thông điệp “Nâng cao nhận thức định hướng tương lai”, sẽ được diễn ra trong một buổi sáng duy nhất vào ngày 27/12/2015 tại số 10 Tân Trào, P. Tân Phú, Q.7, TP.HCM, đánh dấu sự trở lại mùa thứ 2 thành công của chương trình “Chạy bộ vào tương lai gây quỹ trao xe đạp cho trẻ em nghèo năm 2014” do Liên đoàn lãnh đạo và doanh nhân trẻ thế giới tại Việt Nam (JCI VietNam) – Trực thuộc Hội Doanh Nhân Trẻ TP.HCM (YBA) phối hợp cùng cơ quan chức năng địa phương thực hiện.

Với ý nghĩa thiết thực đối với cộng đồng của chương trình, Phòng Công tác Học sinh - Sinh viên thông báo đến tất cả Sinh viên có nguyện vọng tham gia chương trình “Run to Future 2015” vui lòng đăng ký trực tiếp tại https://docs.google.com/forms/d/1e4nSWJ1HKQSOiuIWFMr2AuGHEM7lErCE0Fe2FvZrvtg/viewform

Thời gian đăng ký: từ ngày ra thông báo đến 15g30 ngày 26/12/2015  

Số lượng: 2000 sinh viên

Thời gian chương trình:  06h30-10h00 ngày 27/12/2015(Chủ nhật)

Địa điểm: số 10 Tân Trào, P. Tân Phú, Q.7, TP.HCM

Lộ trình: 3 km

Quyền lợi của Sinh viên:

 - Được Ban Tổ chức hỗ trợ áo, nón đồng phục
 - Mỗi sinh viên được cộng điểm rèn luyện sinh viên theo quy định

Chúc các bạn Sinh viên thành công trong học tập và rèn luyện.

Thân chào!', 6, '2018-11-12 00:00:00');
INSERT INTO public.notification ("NOTIFICATIONID", "TITLE", "CONTENT", "ACTIVITYID", "CREATED_AT") VALUES (7, 'Đăng ký tham dự hội thảo ''Khởi nghiệp đổi mới sáng tạo trong kỉ nguyên số''', 'Các bạn sinh viên thân mến,
 
Nhằm giúp sinh viên Trường có cơ hội giao lưu chia sẻ trực tiếp với lãnh đạo doanh nghiệp, chuyên gia về các vấn đề khởi nghiệp, định hướng các kĩ năng cần thiết, cập nhật tổng quan về xu hướng khởi nghiệp; Phòng công tác học sinh – sinh viên thông báo đến các bạn nội dung cụ thể của buổi hội thảo ''Khởi nghiệp đổi mới sáng tạo trong kỷ nguyên số'' như sau:
   1. Thời gian: 06h50 đến 11h00, ngày 10 tháng 10 năm 2018 (thứ Tư).
   2. Địa điểm: Hội trường 6B.
   3. Lợi ích sinh viên khi tham gia hội thảo:
           Được tìm hiểu tư duy khởi nghiệp, hành trang, kĩ năng cần thiết trong quá trình khởi nghiệp.
           Được chia sẻ những bài học thực tế về khởi nghiệp từ các lãnh đạo doanh nghiệp, chuyên gia.
           Có cơ hội tiếp cận các chính sách hỗ trợ về khởi nghiệp từ các doanh nghiệp.
           Được tư vấn, trải nghiệm trực tiếp về các sản phẩm khởi nghiệp, từ đó thúc đẩy ý tưởng khởi nghiệp.
Sinh viên đăng ký tham dự theo đường link: TẠI ĐÂY.

***Lưu ý: Các bạn sinh viên vui lòng có mặt trước 15 phút để ổn định chỗ ngồi.

Thân chào!', NULL, '2018-11-11 00:00:00');
INSERT INTO public.notification ("NOTIFICATIONID", "TITLE", "CONTENT", "ACTIVITYID", "CREATED_AT") VALUES (8, 'Đăng ký tham dự buổi chuyên đề “Công dân toàn cầu – Bạn sẽ ở đâu trong thế kỷ 21”', 'Các bạn sinh viên thân mến,

Nhằm giúp các bạn sinh viên Trường tự định vị mình trong bối cảnh toàn cầu hóa; những cơ hội và thách thức đối với công dân toàn cầu; từ đó chuẩn bị sẵn cho mình những kiến thức, kỹ năng cần thiết để thành công; Phòng Công tác học sinh – sinh viên thông báo đến các bạn nội dung cụ thể của buổi hội thảo như sau:
         - Thời gian buổi hội thảo: 9h30 đến 11h50, ngày 26 tháng 10 năm 2018 (thứ Sáu).
          - Địa điểm: Hội trường 2A.
          - Diễn giả: Chuyên gia giáo dục độc lập Bùi Khánh Nguyên.
          - Sinh viên vui lòng đăng ký theo đường link: tại đây.
***Lưu ý: Các bạn sinh viên vui lòng có mặt trước 15 phút để ổn định chỗ ngồi.

Mọi thắc mắc vui lòng liên hệ trực tiếp Phòng Công tác học sinh – sinh viên (A003).

Thân chào!', NULL, '2018-11-05 00:00:00');
INSERT INTO public.notification ("NOTIFICATIONID", "TITLE", "CONTENT", "ACTIVITYID", "CREATED_AT") VALUES (9, 'ĐĂNG KÝ THAM DỰ TỌA ĐÀM “NGỌN LỬA BIỂN ĐẢO TRONG LÒNG SINH VIÊN”', 'Thân chào các bạn sinh viên,

Chương trình tọa đàm “Ngọn lửa biển đảo trong lòng sinh viên” nằm trong chuỗi hoạt động của “Hành trình tìm kiếm Đại sứ Đại Dương Xanh” trên 63 tỉnh thành, nhằm hướng đến việc tuyên truyền, quản lý, khai thác, bảo vệ và phát triển bền vững tài nguyên môi trường biển, hải đảo. Thông qua chương trình tọa đàm nhằm giao lưu gắn kết và giải đáp các thắc mắc của các bạn sinh viên trong các vấn đề về môi trường biển đảo và tìm kiếm, tôn vinh vẻ đẹp toàn diện: Tâm - Tầm - Tài - Trí, tôn vinh những trái tim yêu biển, nhiệt huyết cống hiến cho xã hội trong thời đại mới qua việc phát động tìm kiếm các “Đại sứ Đại Dương Xanh” trong sinh viên.

Đến với chương trình các bạn sinh viên sẽ được trao đổi và lắng nghe những chia sẻ đến từ các chuyên gia hàng đầu tại Việt Nam. Họ là những chuyên gia về môi trường, doanh nhân, những người có tầm ảnh hưởng tốt trong cộng đồng xã hội,… Qua đó, giúp các bạn sinh viên nâng cao nhận thức, ý nghĩa trách nhiệm đối với công tác bảo vệ môi trường biển, hải đảo; ứng phó với thiên tai, thích ứng với biến đổi khí hậu toàn cầu; cũng như tăng cường xây dựng, quảng bá thành tựu, thương hiệu biển quốc gia, đẩy mạnh phát triển kinh tế biển Việt Nam.

Ngoài ra, chương trình còn là đêm văn nghệ giao lưu nghệ thuật với các tiết mục đặc sắc đến từ các bạn sinh viên Trường đại học Tôn Đức Thắng và giao lưu với các ca sỹ, nghệ sỹ nổi tiếng tại Việt Nam như: nhóm nhạc V-Music, ca sỹ Châu Khải Phong, Hồng Vân, Fami Ngô, Cao Tùng Huy, Hoài Nhung, nhạc sỹ Thế Hiển, đạo diễn Lê Công Bắc,…

***Thông tin về chương trình:

     1. Địa điểm: Hội trường A - Trường đại học Tôn Đức Thắng.

     2. Thời gian tổ chức: 18h00 - 21h00, Thứ Sáu, ngày 02/11/2018.

     3. Link đăng ký tham gia chương trình: tại đây.

Phòng CT-HSSV, Đoàn thanh niên – Hội sinh viên trường thân mời các bạn sinh viên dành thời gian đến tham gia và giao lưu tại chương trình.

Chúc các bạn sinh viên học tập và rèn luyện thật tốt!', NULL, '2018-11-04 00:00:00');
INSERT INTO public.notification ("NOTIFICATIONID", "TITLE", "CONTENT", "ACTIVITYID", "CREATED_AT") VALUES (10, 'ĐĂNG KÝ THAM DỰ BUỔI TỌA ĐÀM ''HIỂU - BIẾT - PHÒNG TRÁNH HIV/AIDS VÀ BỆNH LÂY QUA ĐƯỜNG TÌNH DỤC''', 'Các bạn sinh viên thân mến,


Nhằm giúp các bạn sinh viên Trường hiểu, biết, phòng tránh HIV/AIDS và bệnh lây qua đường tình dục bằng buổi tọa đàm với các chuyên gia, bác sỹ cũng như nghệ sỹ khách mời; Phòng Công tác học sinh - sinh viên thông báo đến các bạn nội dung cụ thể của buổi tọa đàm như sau:         
     - Thời gian buổi tọa đàm: 9h30 đến 11h50, ngày 29 tháng 8 năm 2018 (thứ Tư).
     - Địa điểm: Hội trường 6B.
Ngoài ra, các bạn sinh viên tham dự sẽ nhận được 01 phần quà từ chương trình.
***Lưu ý: Các bạn sinh viên vui lòng có mặt trước 15 phút để ổn định chỗ ngồi.
      - Sinh viên vui lòng đăng ký theo đường link: tại đây.
Mọi thắc mắc vui lòng liên hệ trực tiếp Phòng Công tác học sinh – sinh viên (A003).

Thân chào!', NULL, '2018-11-04 00:00:00');


--
-- TOC entry 2871 (class 0 OID 17009)
-- Dependencies: 202
-- Data for Name: semester; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.semester ("SEMESTERID", "SEMESTER", "YEARS") VALUES (1, 'HK1', '2017-2018');
INSERT INTO public.semester ("SEMESTERID", "SEMESTER", "YEARS") VALUES (2, 'HK2', '2017-2018');
INSERT INTO public.semester ("SEMESTERID", "SEMESTER", "YEARS") VALUES (3, 'HK1', '2018-2019');
INSERT INTO public.semester ("SEMESTERID", "SEMESTER", "YEARS") VALUES (4, 'HK2', '2018-2019');


--
-- TOC entry 2868 (class 0 OID 16963)
-- Dependencies: 199
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.student ("STUDENTID", "STUDENTNAME", "EMAIL", "BIRTHDAY", "GENDER", "CLASS", "DEPARTMENT") VALUES ('01', 'Lương Văn Kiệt', 'lvk@gmail.com', '1997-11-04 00:00:00', 'Nam', 'C01', 'Khoa học máy tính');
INSERT INTO public.student ("STUDENTID", "STUDENTNAME", "EMAIL", "BIRTHDAY", "GENDER", "CLASS", "DEPARTMENT") VALUES ('02', 'Cao Ngọc Như Quỳnh', 'cnnq@gmail.com', '1997-06-03 00:00:00', 'Nữ', 'C01', 'Khoa học máy tính');
INSERT INTO public.student ("STUDENTID", "STUDENTNAME", "EMAIL", "BIRTHDAY", "GENDER", "CLASS", "DEPARTMENT") VALUES ('03', 'Uông Ngọc Bích', 'unb@gmail.com', '1997-08-03 00:00:00', 'Nữ', 'C03', 'Khoa học máy tính');
INSERT INTO public.student ("STUDENTID", "STUDENTNAME", "EMAIL", "BIRTHDAY", "GENDER", "CLASS", "DEPARTMENT") VALUES ('04', 'NGuyễn Thị Hoa', 'nth@gmail.com', '1997-02-03 00:00:00', 'Nữ', 'C07', 'Khoa kế toán');
INSERT INTO public.student ("STUDENTID", "STUDENTNAME", "EMAIL", "BIRTHDAY", "GENDER", "CLASS", "DEPARTMENT") VALUES ('05', 'Sinh viên', 'sinhvien@gmail.com', '1997-11-11 00:00:00', 'Nam', 'C01', 'Khoa quản trị kinh doanh');


--
-- TOC entry 2869 (class 0 OID 16971)
-- Dependencies: 200
-- Data for Name: student_activity; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2872 (class 0 OID 17018)
-- Dependencies: 203
-- Data for Name: student_semester; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2881 (class 0 OID 0)
-- Dependencies: 204
-- Name: ACTIVITYID_AI; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."ACTIVITYID_AI"', 5, true);


--
-- TOC entry 2882 (class 0 OID 0)
-- Dependencies: 197
-- Name: NOTIFICATIONID_seqde16fa6991404131ab676ff9d28fd353; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."NOTIFICATIONID_seqde16fa6991404131ab676ff9d28fd353"', 1, false);


--
-- TOC entry 2883 (class 0 OID 0)
-- Dependencies: 205
-- Name: SEMESTERID_SEQ; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."SEMESTERID_SEQ"', 4, true);


--
-- TOC entry 2737 (class 2606 OID 17036)
-- Name: activity PKEY_ACTIVITYID_ACTIVITY; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.activity
    ADD CONSTRAINT "PKEY_ACTIVITYID_ACTIVITY" PRIMARY KEY ("ACTIVITYID");


--
-- TOC entry 2735 (class 2606 OID 17017)
-- Name: student_activity PKEY_STUDENTID_ACTIVITYID_STUDENTACTIVITY; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.student_activity
    ADD CONSTRAINT "PKEY_STUDENTID_ACTIVITYID_STUDENTACTIVITY" PRIMARY KEY ("STUDENTID", "ACTIVITYID");


--
-- TOC entry 2741 (class 2606 OID 17022)
-- Name: student_semester PKEY_STUDENTID_SEMESTERID_STUDENTSEMESTER; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.student_semester
    ADD CONSTRAINT "PKEY_STUDENTID_SEMESTERID_STUDENTSEMESTER" PRIMARY KEY ("STUDENTID", "SEMESTERID");


--
-- TOC entry 2725 (class 2606 OID 16938)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY ("USERNAME");


--
-- TOC entry 2729 (class 2606 OID 16987)
-- Name: notification notification_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY ("NOTIFICATIONID");


--
-- TOC entry 2739 (class 2606 OID 17013)
-- Name: semester pkey_semesterID_semester; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.semester
    ADD CONSTRAINT "pkey_semesterID_semester" PRIMARY KEY ("SEMESTERID");


--
-- TOC entry 2731 (class 2606 OID 16970)
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY ("STUDENTID");


--
-- TOC entry 2727 (class 2606 OID 16984)
-- Name: account ukey_email_account; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT ukey_email_account UNIQUE ("EMAIL");


--
-- TOC entry 2733 (class 2606 OID 16977)
-- Name: student ukey_email_student; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT ukey_email_student UNIQUE ("EMAIL");


--
-- TOC entry 2743 (class 2606 OID 17028)
-- Name: student_semester FKEY_SEMESTERID_STUDENT_SEMESTER; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.student_semester
    ADD CONSTRAINT "FKEY_SEMESTERID_STUDENT_SEMESTER" FOREIGN KEY ("SEMESTERID") REFERENCES public.semester("SEMESTERID") ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2742 (class 2606 OID 17023)
-- Name: student_semester FKEY_STUDENTID_STUDENT; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.student_semester
    ADD CONSTRAINT "FKEY_STUDENTID_STUDENT" FOREIGN KEY ("STUDENTID") REFERENCES public.student("STUDENTID") ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2018-12-05 16:42:08

--
-- PostgreSQL database dump complete
--

