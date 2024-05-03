INSERT INTO NhanVien
VALUES
	('NV001', N'Nguyễn Võ', N'Hiệp', 19, 0, N'197 Trần Bá Giao, phường 5, Gò Vấp, HCM', N'0394767463', 5000000, N'Tối'),
	('NV002', N'Nguyễn', N'Gia Hào', 20, 0, N'Tân Bình, HCM', N'0351239200', 5000000, N'Sáng'),
	('NV003', N'Lê', N'Quang Huy', 20, 0, N'01 Lê Đức Thọ, phường 7, Gò Vấp, HCM', N'0328192212', 5000000, N'Sáng'),
	('NV004', N'Nguyễn', N'Hoàng Khải', 21, 0, N'Đường số 10 , phường Linh Tây, Thủ Đức, HCM', N'0389521282', 5000000, N'Chiều'),
	('NV005', N'Lê', N'Thị Thúy Hiền', 19, 1, N'17 Vườn Lài, phường An Phú Đông, Quận 12, HCM', N'0351001211', 5000000, N'Chiều')
GO
INSERT INTO [dbo].[KhachHang] ([MaKH], [TenKH], [DiaChi], [SoDT], [DCMail], [DiemTL])
VALUES
    (N'KH0001', N'Nguyễn Văn A', N'Hà Nội', N'0320121200', N'nguyenvana@gmail.com', 10000),
    (N'KH0002', N'Trần Thị B', N'Hồ Chí Minh', N'0987654321', N'tranthib@gmail.com', 20000),
    (N'KH0003', N'Lê Văn C', N'Đà Nẵng', N'0369855005', N'levanc@gmail.com', 15000),
    (N'KH0004', N'Phạm Thị D', N'Hải Phòng', N'0352920010', N'phamthid@gmail.com', 100000),
    (N'KH0005', N'Nguyễn Văn E', N'Hà Nội', N'0987654320', N'nguyenvane@gmail.com', 20000),
    (N'KH0006', N'Trần Thị F', N'Hồ Chí Minh', N'0369852147', N'tranthif@gmail.com', 150000),
    (N'KH0007', N'Lê Văn G', N'Đà Nẵng', N'0369101291', N'levang@gmail.com', 10000),
    (N'KH0008', N'Phạm Thị H', N'Hải Phòng', N'0981911111', N'phamthih@gmail.com', 20000),
    (N'KH0009', N'Nguyễn Văn I', N'Hà Nội', N'0369852222', N'nguyenvani@gmail.com', 15000),
    (N'KH0010', N'Trần Thị J', N'Hồ Chí Minh', N'0389101000', N'tranthij@gmail.com', 10000),
    (N'KH0011', N'Lê Văn K', N'Đà Nẵng', N'0988881111', N'levank@gmail.com', 50000),
    (N'KH0012', N'Phạm Thị L', N'Hải Phòng', N'0366361221', N'phamthil@gmail.com', 10000),
    (N'KH0013', N'Nguyễn Văn M', N'Hà Nội', N'0522011055', N'nguyenvanm@gmail.com', 20000),
    (N'KH0014', N'Trần Thị N', N'Hồ Chí Minh', N'0398199999', N'tranthin@gmail.com', 15000),
    (N'KH0015', N'Lê Văn O', N'Đà Nẵng', N'0331231101', N'levano@gmail.com', 100000),
    (N'KH0016', N'Phạm Thị P', N'Hải Phòng', N'0355351001', N'phamthip@gamil.com', 20000),
    (N'KH0017', N'Nguyễn Văn Q', N'Hà Nội', N'0323312001', N'nguyenvanq@gmail.com', 15000),
    (N'KH0018', N'Trần Thị R', N'Hồ Chí Minh', N'0365678901', N'tranthir@gmail.com', 10000),
    (N'KH0019', N'Lê Văn S', N'Đà Nẵng', N'0350102357', N'levans@gmail.com', 20000),
    (N'KH0020', N'Phạm Thị T', N'Hải Phòng', N'0989091020', N'phamthit@gmail.com', 30000)
GO
INSERT INTO NhaCungCap (MaNCC, TenNCC, DiaChi, SoDT, DCMail)
VALUES
    ('NCC001', 'Coca Cola', 'HCM', '0123456789', 'cocacola@example.com'),
    ('NCC002', 'Pepsi', 'HCM', '0987654321', 'pepsi@example.com'),
    ('NCC003', 'Sting', 'HCM', '0998877665', 'sting@example.com'),
    ('NCC004', 'Red Bull', 'Thailand', '0765432109', 'redbull@example.com'),
    ('NCC005', 'Number One', 'HCM', '0111222333', 'num1@example.com'),
    ('NCC006', 'Revive', 'HCM', '0876543210', 'revive@example.com'),
    ('NCC007', 'Aquafina', 'HCM', '0898776554', 'aquafina@example.com'),
    ('NCC008', 'Vinh Hao', 'HCM', '0890123456', 'vinhhao@example.com'),
    ('NCC009', 'C2', 'HCM', '0789456123', 'c2@example.com'),
    ('NCC010', 'Nutriboost', 'HCM', '0999555444', 'nutriboost@example.com'),
    ('NCC011', 'Oishi', 'HCM', '0567890123', 'oshi@example.com'),
    ('NCC012', 'Orion', 'HCM', '0102030405', 'orion@example.com'),
    ('NCC013', 'Kinh Do', 'HCM', '0765432987', 'kinhdo@example.com'),
    ('NCC014', 'Poca', 'HCM', '0123456709', 'poca@example.com'),
    ('NCC015', 'Lays', 'Ha Noi', '0876543219', 'lay@example.com'),
    ('NCC016', 'Pringles', 'Ha Noi', '0890765432', 'spingles@example.com'),
    ('NCC017', 'Oreo', 'Ha Noi', '0987654320', 'oreo@example.com'),
    ('NCC018', 'Choco Pie', 'Ha Noi', '0901234567', 'chocopie@example.com'),
    ('NCC019', 'Bento', 'Ha Noi', '0888888888', 'bento@example.com'),
    ('NCC020', 'Ostar', 'Ha Noi', '0777777777', 'ostar@example.com'),
    ('NCC021', 'Hao Hao', 'Ha Noi', '0999888777', 'acecook@example.com'),
    ('NCC022', 'Omachi', 'Ha Noi', '0654321098', 'masan@example.com'),
    ('NCC023', 'Kokomi', 'Ha Noi', '0876543210', 'masan@example.com'),
    ('NCC024', 'Indomie', 'Ha Noi', '0543210987', 'indomie@example.com'),
    ('NCC025', 'CayKay', 'Ha Noi', '0567890234', 'acecook@example.com'),
    ('NCC026', 'Nissin', 'Ha Noi', '0654321876', 'nissin@example.com'),
    ('NCC027', 'Miliket', 'HCM', '0543210765', 'miliket@example.com'),
    ('NCC028', 'Gau do', 'HCM', '0698798987', 'gaudo@example.com'),
    ('NCC029', 'Modern', 'HCM', '0387697879', 'acecook@example.com'),
    ('NCC030', 'Samyang', 'HCM', '0687998798', 'samyang@example.com'),
    ('NCC031', 'Cung Dinh', 'HCM', '0417490080', 'cungdinh@example.com'),
    ('NCC032', 'Merino', 'HCM', '0710942814', 'merino@example.com'),
    ('NCC033', 'TH', 'HCM', '0710942815', 'th@example.com'),
    ('NCC034', 'Vinamilk', 'HCM', '0710942864', 'vinamilk@example.com'),
    ('NCC035', 'Dutch Lady', 'HCM', '0710942817', 'dutchlady@example.com'),
    ('NCC036', 'YoMost', 'HCM', '0710942818', 'yomost@example.com'),
    ('NCC037', 'Kun', 'HCM', '0710942819', 'kum@example.com'),
    ('NCC038', 'Milo', 'HCM', '0710942810', 'milo@example.com'),
    ('NCC039', 'Closeup', 'HCM', '0710942811', 'closeup@example.com'),
    ('NCC040', 'Sensodyne', 'HCM', '0710942812', 'sensodyne@example.com'),
    ('NCC041', 'Colgate', 'HCM', '0710942813', 'cocacola@example.com'),
    ('NCC042', 'Eucryl', 'HCM', '0710942814', 'eucryl@example.com'),
    ('NCC043', 'Ngoc Chau', 'HCM', '0710942815', 'ngocchau@example.com'),
    ('NCC044', 'Crest', 'HCM', '0710942816', 'crest@example.com'),
    ('NCC045', 'Thai Duong', 'HCM', '0710942817', 'thaiduong@example.com'),
    ('NCC046', 'PS', 'HCM', '0710942818', 'ps@example.com'),
    ('NCC047', 'ABA', 'HCM', '0710942819', 'aba@example.com'),
    ('NCC048', 'SURF', 'HCM', '0710942810', 'suft@example.com'),
    ('NCC049', 'ARIEL', 'HCM', '0710942811', 'ariel@example.com'),
    ('NCC050', 'OMO', 'HCM', '0710942812', 'omo@example.com'),
    ('NCC051', 'Comfor', 'HCM', '0710942813', 'comfor@example.com'),
    ('NCC052', 'Downy', 'HCM', '0710942814', 'downy@example.com'),
    ('NCC053', 'Chupa Chups', 'HCM', '0710942815', 'chupachups@example.com'),
    ('NCC054', 'Dynamite', 'HCM', '0710942816', 'dynamite@example.com'),
    ('NCC055', 'Cosy', 'HCM', '0710942817', 'cosy@example.com'),
    ('NCC056', 'One One', 'HCM', '0710942818', 'oneone@example.com'),
    ('NCC057', 'Median Dental', 'HCM', '0710942819', 'mediandental@example.com'),
    ('NCC058', 'Cong Ty TNHH Thuc Pham Tien Phat', 'HCM', '0710942820', 'tptienphat@example.com'),
    ('NCC059', 'Trung Nguyen', 'HCM', '0710942821', 'trungnguyen@example.com')
GO
INSERT INTO NhomSanPham
VALUES
	('NuocGiaiKhat', N'Nước giải khát'),
	('NuocUong', N'Nước uống'),
	('NuocXa', N'Nước xả'),
	('BanhKeo', N'Bánh kẹo'),
	('BotGiat', N'Bột giặt'),
	('DoAn', N'Đồ ăn'),
	('KemMerio', N'Kem Merio'),
	('MiGoi', N'Mì gói'),
	('NuocGiat', N'Nước giặt'),
	('Sua', N'Sữa'),
	('SuaChua', N'Sữa chua'),
	('Snack', N'Snack'),
	('KemTH', N'Kem TH'),
	('KemDanhRang', N'Kem đánh răng')
GO
INSERT INTO SanPham (MaSP, TenSP, MaNCC, MoTa, MaNhom, DonViTinh, GiaNhap, NgayNhap, NgaySX, NgayHH, SLTON)
VALUES
    --Nước uống
    ('SP0001', 'Coca Cola khong duong', 'NCC001', 'Nuoc co ga', 'NuocGiaiKhat', 'Chai', 8000, '2024-03-12', '2024-04-12', '2025-04-12', 100),
    ('SP0002', 'Diet Pepsi', 'NCC002', 'Nuoc co ga', 'NuocGiaiKhat', 'Chai', 8000, '2024-01-10', '2024-03-26', '2025-03-26', 80),
    ('SP0003', 'Sting vi dau', 'NCC003', 'Nuoc co ga', 'NuocGiaiKhat', 'Chai', 7500, '2024-01-26', '2024-03-26', '2025-03-26', 100),
    ('SP0004', 'Red Bull','NCC004', 'Nuoc co ga', 'NuocGiaiKhat', 'Lon', 12000, '2024-01-26', '2024-03-26', '2025-03-26', 50),
    ('SP0005', 'Number One', 'NCC005', 'Nuoc co ga', 'NuocGiaiKhat', 'Lon', 9000, '2024-01-26', '2024-03-12', '2025-03-12', 90),
    ('SP0006', 'Revive', 'NCC006', 'Nuoc co ga', 'NuocGiaiKhat', 'Chai', 8000, '2024-01-26', '2024-03-22', '2025-03-22', 100),
    ('SP0007', 'Aquafina','NCC007', 'Nuoc khong co ga', 'NuocUong', 'Chai', 8000, '2024-01-26', '2024-02-12', '2026-02-12', 55),
    ('SP0008', 'Cafe sua', 'NCC059', 'Cafe sua', 'NuocUong', 'Ly', 15000, '2024-02-26', '2024-03-26', '2025-03-26', 100),
    ('SP0009', 'Vinh Hao', 'NCC008', 'Nuoc khong co ga', 'NuocUong', 'Chai', 9000, '2024-01-26', '2024-02-26', '2025-02-26', 90),
    ('SP0010', 'Cafe đen', 'NCC059', 'Cafe đen', 'NuocUong', 'Ly', 15000, '2024-01-26', '2024-02-23', '2025-02-23', 100),
    ('SP0011', 'C2 chanh', 'NCC009', 'Nuoc khong co ga', 'NuocUong', 'Chai', 10000, '2024-01-26', '2024-02-24', '2025-02-24', 100),
    ('SP0012', 'Nutriboost', 'NCC010', 'Nuoc khong co ga', 'NuocUong', 'Chai', 15000, '2024-02-25', '2024-03-26', '2025-03-26', 50),

    --Snack
    ('SP0013', 'Oishi Crab me', 'NCC011', 'Snack vị cua sot chua ngot', 'Snack', 'Tui', 4000, '2024-01-26', '2024-02-13', '2025-01-13', 100),
    ('SP0014', 'Corn chip', 'NCC011', 'Snack vi bap', 'Snack', 'Tui', 10000, '2024-01-26', '2024-02-10', '2025-02-10', 100),
    ('SP0015', 'Poca BBQ', 'NCC014', 'Snack vi BBQ', 'Snack', 'Tui', 10000, '2024-01-26', '2024-02-02', '2025-02-02', 90),
    ('SP0016', 'Lays BBQ', 'NCC015', 'Snack vi BBQ', 'Snack', 'Tui', 10000, '2024-01-26', '2024-03-05', '2025-03-05', 60),
    ('SP0017', 'Pringles BBQ','NCC011', 'Snack vi BBQ', 'Snack', 'Hop', 15000, '2024-01-26', '2024-03-15', '2025-03-15', 110),
    ('SP0018', 'Lays ga teriyaki', 'NCC015', 'Snack vi ga Teriyaki Nhat Ban', 'Snack', 'Tui', 10000, '2024-01-24', '2024-01-30', '2025-01-30', 120),
    ('SP0019', 'Oreo Socola', 'NCC017', 'Banh quy vi socola', 'Snack', 'Hop', 12000, '2024-01-26', '2024-02-28', '2025-02-28', 90),
    ('SP0020', 'Choco Pie','NCC018', 'Banh vi socola', 'Snack', 'Hop', 12000, '2024-01-26', '2024-02-01', '2025-02-01', 70),
    ('SP0021', 'Muc Bento', 'NCC012', 'Snack Hai san tam muc Bento', 'Snack', 'Hop', 20000, '2024-01-26', '2024-03-01', '2025-01-01', 150),
    ('SP0022', 'Ostar kim chi', 'NCC012', 'Snack vi kim chi', 'Snack', 'Hop', 10000, '2024-02-26', '2024-02-28', '2025-02-28', 50),

    --Đồ ăn
    ('SP0023', 'Mi tron xuc xich trung', 'NCC058', 'Mi tron xuc xich trung', 'DoAn', 'Hop', 10000, NULL, NULL, NULL, 50),
    ('SP0024', 'Banh bao','NCC058', 'Banh bao', 'DoAn', 'Hop', 15000, NULL, NULL, NULL, 100),
    ('SP0025', 'Banh mi', 'NCC058', 'Banh mi', 'DoAn', 'Hop', 9000, NULL, NULL, NULL, 50),
    ('SP0026', 'Mi tron Indomie', 'NCC058', 'Mi tron Indomie', 'DoAn', 'Tui', 10000, NULL, NULL, NULL, 70),
    ('SP0027', 'Lau ly', 'NCC058', 'Lau ly', 'DoAn', 'Hop', 15000, '2024-01-20', '2024-01-28', '2024-02-05', 100),
    ('SP0028', 'Dui ga nuong', 'NCC058', 'Dui ga nuong', 'DoAn', 'Hop', 20000, NULL, NULL, NULL, 50),
    ('SP0029', 'Xuc xich', 'NCC058', 'Xuc xich', 'DoAn', 'Hop', 5000,NULL, NULL, NULL, 100),
    ('SP0030', 'Chuoi', 'NCC058', 'Chuoi', 'DoAn', 'Hop', 20000, '2024-01-30', '2024-02-04', '2024-02-10', 30),
    ('SP0031', 'Sandwich', 'NCC058', 'Sandwich', 'DoAn', 'Hop', 18000, NULL, NULL, NULL, 100),
    ('SP0032', 'Banh mi que','NCC058', 'Banh mi que', 'DoAn', 'Hop', 10000, '2024-01-30', '2024-02-05', '2024-02-15', 70),
    ('SP0033', 'Com nam', 'NCC058', 'Com nam', 'DoAn', 'Hop', 10000,'2024-01-30', '2024-02-01', '2024-02-10', 60),
    ('SP0034', 'Hamburger', 'NCC058', 'Hamburger', 'DoAn', 'Hop', 15000, '2024-01-26', '2024-02-01', '2024-02-10', 60),
    ('SP0035', 'Pizza', 'NCC058', 'Pizza', 'DoAn', 'Hop', 30000,NULL ,NULL,NULL, 100),

    --Mì gói
    ('SP0036', 'Mi goi Hao Hao tom chua cay', 'NCC021', 'Mi goi Hao Hao', 'MiGoi', 'Goi', 4000, '2024-01-26', '2024-03-07', '2025-03-07', 90),
    ('SP0037', 'Mi goi Omachi bo ham', 'NCC022', 'Mi goi Omachi', 'MiGoi', 'Goi', 8000, '2024-01-26', '2024-02-28', '2025-02-28' ,80),
    ('SP0038', 'Mi goi Kokomi tom chua cay', 'NCC023', 'Mi goi Kokomi', 'MiGoi', 'Goi', 10000, '2024-02-26', '2024-03-07', '2025-03-07', 100),
    ('SP0039', 'Mi goi Indomie Mi Goreng', 'NCC024', 'Mi goi Indomie', 'MiGoi', 'Goi', 12000, '2024-01-26', '2024-03-13', '2025-03-13', 110),
    ('SP0040', 'Mi ly CayKay vi bo', 'NCC025', 'Mi ly CayKay', 'MiGoi', 'Ly', 11000, '2024-02-26', '2024-03-07', '2025-03-07', 100),
    ('SP0041', 'Mi goi Nissin tom chua cay', 'NCC026', 'Mi goi Nissin', 'MiGoi', 'Goi', 5000,'2024-02-26', '2024-03-28', '2025-03-28' ,100),
    ('SP0042', 'Mi goi Miliket tom chua cay', 'NCC027', 'Mi goi Miliket', 'MiGoi', 'Goi', 6000, '2024-01-26', '2024-02-28', '2025-02-28', 150),
    ('SP0043', 'Mi goi Gau do tom ga', 'NCC028', 'Mi goi Gau do', 'MiGoi','Goi', 5000, '2023-12-26', '2024-01-26', '2025-01-26', 100),
    ('SP0044', 'Mi goi Modern lau Thai tom','NCC029', 'Mi goi Modern', 'MiGoi', 'Goi', 6000, '2024-01-26', '2024-01-28', '2025-01-28', 100),
    ('SP0045', 'Mi goi Cung dinh suon ham ngu qua', 'NCC031', 'Mi goi Cung dinh', 'MiGoi', 'Goi', 9000,'2024-01-26', '2024-03-07', '2025-03-07', 100),
    ('SP0046', 'Mi goi Samyang pho mai', 'NCC030', 'Mi goi Samyang', 'MiGoi', 'Goi', 33000, '2024-02-26', '2024-03-07', '2025-03-07', 80),
    ('SP0047', 'Mi goi Cung Dinh Kool suon nuong to', 'NCC031', 'MiGoi', 'MiGoi','Goi', 15000, '2024-02-26', '2024-02-28', '2025-02-28' ,70),


    --Kem
    ('SP0048', 'Kem Merino Steen socola ', 'NCC032', 'Kem oc que Merio', 'KemMerio', 'Cai', 8000, '2024-02-26', '2024-02-28', '2025-02-28' ,40),
    ('SP0049', 'Kem Merino Steen vani', 'NCC032', 'Kem oc que Merio', 'KemMerio', 'Cai', 8000, '2024-01-26', '2024-03-07', '2025-03-07', 50),
    ('SP0050', 'Kem Merino que dau do','NCC032', 'Kem que Merio',  'KemMerio', 'Cai', 9000, '2024-01-26', '2024-03-13', '2025-03-13', 20),
    ('SP0051', 'Kem Merino que dau xanh', 'NCC032', 'Kem que Merio', 'KemMerio', 'Cai', 9000, '2024-01-26', '2024-03-07', '2025-03-07', 50),
    ('SP0052', 'Kem Merino que sau rieng', 'NCC032', 'Kem que Merio', 'KemMerio', 'Cai', 9000,'2024-02-26', '2024-04-28', '2025-04-28' ,30),
    ('SP0053', 'Kem Merino que dua hau', 'NCC032', 'Kem que Merio',  'KemMerio', 'Cai', 9000, '2024-02-26', '2024-03-28', '2025-03-28', 50),
    ('SP0054', 'Kem hộp Merio vi dua', 'NCC032', 'Kem hop Merio', 'KemMerio', 'Hop', 50000, '2024-02-26', '2024-02-26', '2025-02-26', 20),
    ('SP0055', 'Kem hộp Merio socola', 'NCC032', 'Kem hop Merio', 'KemMerio', 'Hop', 50000, '2024-01-12', '2024-01-28', '2025-01-28', 40),
    ('SP0056', 'Kem hộp Merio mix  ','NCC032', 'Kem hop Merio',  'KemMerio', 'Hop', 50000,'2024-01-26', '2024-03-07', '2025-03-07', 50),
    ('SP0057', 'Kem que TH vi dua', 'NCC033', 'Kem que TH', 'KemTH', 'Cai', 10000, '2024-01-26', '2024-03-07', '2025-03-07', 40),
    ('SP0058', 'Kem que TH vi socola', 'NCC033', 'Kem que TH', 'KemTH', 'Cai', 10000, '2024-02-26', '2024-03-28', '2025-03-28' ,20),

  
    ('SP0059', 'Sua Milo ', 'NCC038', 'Sua Milo', 'Sua', 'Hop', 6000, '2024-02-26', '2024-02-28', '2025-02-28' ,40),
    ('SP0060', 'Sua TH vi socola', 'NCC033', 'Sua socola', 'Sua', 'Hop', 6000, '2024-02-26', '2024-03-07', '2025-03-07', 50),
    ('SP0061', 'Sua TH vi dau','NCC033', 'Sua dau', 'Sua', 'Hop', 6000, '2024-01-10', '2024-01-13', '2025-01-13', 20),
    ('SP0062', 'Sua TH nguyen chat', 'NCC033', 'Sua tuoi', 'Sua', 'Hop', 6000, '2024-01-26', '2024-03-07', '2025-03-07', 50),
    ('SP0063', 'Sua kun', 'NCC037', 'Sua tuoi', 'Sua', 'Hop', 6000,'2024-01-26', '2024-01-28', '2025-01-28' ,30),
    ('SP0064', 'Sua Vinamilk vi socola', 'NCC034', 'Sua socola', 'Sua', 'Hop', 6000, '2024-01-26', '2024-02-28', '2025-02-28', 50),
    ('SP0065', 'Sua tiet trung Dutch Lady co duong', 'NCC035', 'Sua tuoi', 'Sua', 'Hop', 6000, '2023-12-26', '2024-01-26', '2025-09-26', 20),
    ('SP0066', 'Sua tiet trung Dutch Lady khong duong', 'NCC035', 'Sua tuoi', 'Sua', 'Hop', 6000, '2024-02-26', '2024-03-28', '2025-03-28', 40),
    ('SP0067', 'Sua YoMost vi dau','NCC036', 'Sua dau', 'Sua', 'Hop', 6000,'2024-01-26', '2024-03-07', '2025-03-07', 50),
    ('SP0068', 'Sua YoMost vi cam', 'NCC036', 'Sua cam', 'Sua', 'Hop', 6000, '2024-01-26', '2024-03-07', '2025-03-07', 40),
    ('SP0069', 'Sua chua TH ', 'NCC033', 'Sua chua TH', 'SuaChua', 'Hop', 5000, '2024-02-26', '2024-02-28', '2025-02-28' ,20),
    ('SP0070', 'Sua chua Vinamilk ', 'NCC034', 'Sua chua Vinamilk', 'SuaChua', 'Hop', 5000, '2024-02-26', '2024-02-28', '2025-02-28' ,20),
    ('SP0071', 'Sua chua Hy lap ', 'NCC034', 'Sua chua Greek', 'SuaChua', 'Hop', 10000, '2024-01-26', '2024-02-05', '2025-02-05' ,20),
    ('SP0072', 'Sua chua tui', 'NCC034', 'Sua chua nha lam', 'SuaChua', 'Tui', 5000, '2024-03-02', '2024-04-30', '2025-04-30', 100),

    ('SP0073', 'Kem danh rang PS ', 'NCC046', 'Kem danh rang PS','KemDanhRang', 'Hop', 20000, '2024-02-02', '2024-02-28', '2025-02-28',40),
    ('SP0074', 'Kem danh rang Closeup', 'NCC039', 'Kem danh rang closeup', 'KemDanhRang',  'Hop',35000, '2024-02-26', '2024-02-28', '2025-02-28', 50),
    ('SP0075', 'Kem danh rang Sensodyne','NCC040', 'Kem danh rang sensodyne', 'KemDanhRang', 'Hop', 2500,'2024-01-26', '2024-03-07', '2025-03-07', 20),
    ('SP0076', 'Kem danh rang Colgate', 'NCC041', 'Kem danh rang colgate', 'KemDanhRang', 'Hop', 2500, '2024-01-26', '2024-02-28', '2025-02-28', 50),
    ('SP0077', 'Kem danh rang Median Dental IQ 93%', 'NCC057', 'Kem danh rang Median','KemDanhRang', 'Hop', 25000, '2024-02-26', '2024-02-28', '2025-02-28',30),
    ('SP0078', 'Kem danh rang Thai duong', 'NCC045', 'Kem danh rang thai duong', 'KemDanhRang', 'Hop', 2500, '2024-01-26', '2024-02-28', '2025-02-28', 50),
    ('SP0079', 'Kem danh rang Crest', 'NCC044', 'Kem danh rang Crest', 'KemDanhRang', 'Hop', 20000, '2024-02-26', '2024-02-28', '2025-02-28', 20), 
    ('SP0080', 'Kem danh rang Eucryl', 'NCC042', 'Kem danh rang Eucryl', 'KemDanhRang', 'Hop', 20000, '2024-01-26', '2024-02-07', '2025-02-07', 40),
    ('SP0081', 'Kem danh rang Ngoc Chau','NCC043', 'Kem danh rang Ngoc Chau', 'KemDanhRang', 'Hop', 20000,'2024-01-26', '2024-02-28', '2025-02-28', 50),


    ('SP0082', 'Bot giat OMO ', 'NCC050', 'Bot giat OMO', 'BotGiat', 'Tui',20000,'2024-01-26', '2024-01-30', '2026-01-30', 30),
    ('SP0083', 'Bot giat ABA', 'NCC047', 'Bot giat ABA', 'BotGiat', 'Tui',20000,'2024-01-06', '2024-03-01', '2026-03-01', 50),
    ('SP0084', 'Bot giat SURF', 'NCC048', 'Bot giat SURF', 'BotGiat', 'Tui',25000,'2024-02-05', '2024-02-22', '2025-02-22', 60),
    ('SP0085', 'Bot giat ARIEL', 'NCC049', 'Bot giat ARIEL', 'BotGiat', 'Tui',25000,'2024-01-26', '2024-02-12', '2026-02-12', 20),
    ('SP0086', 'Nuoc giat OMO', 'NCC050', 'Nước giặt OMO', 'NuocGiat', 'Tui', 90000,'2024-02-26', '2024-03-28', '2026-03-28',40),
    ('SP0087', 'Nuoc giat Comfor', 'NCC051', 'Nước xã comfor', 'NuocGiat', 'Tui',90000,'2024-01-05', '2024-02-22', '2025-02-22', 50),
    ('SP0088', 'Nuoc xa Downy', 'NCC052', 'Nước xã Downy', 'NuocXa', 'Tui',90000,'2024-01-26', '2024-01-28', '2026-01-28', 60),

    ('SP0089', 'Keo mut chupa chups ', 'NCC053', 'Keo Chupa chups', 'BanhKeo', 'Cai',1000,'2024-01-26', '2024-01-29', '2026-01-29', 30),
    ('SP0090', 'Kẹo deo chupa chups', 'NCC053', 'Keo Chupa chups', 'BanhKeo', 'Tui',9000,'2024-01-26', '2024-03-13', '2026-03-13', 50),
    ('SP0091', 'Keo deo trai cay', 'NCC053', 'Keo trai cay', 'BanhKeo', 'Goi',10000,'2024-01-05', '2024-02-22', '2025-02-22', 60),
    ('SP0092', 'Keo Dynamite', 'NCC054', 'Keo socola', 'BanhKeo', 'Goi',10000,'2024-01-26', '2024-02-12', '2026-02-12', 20),
    ('SP0093', 'Banh gao One One', 'NCC056', 'Banh gao', 'BanhKeo', 'Goi', 20000,'2024-01-26', '2024-02-28', '2026-02-28',40),
    ('SP0094', 'Banh khoai tay', 'NCC056', 'Banh quy', 'BanhKeo', 'Goi',17000,'2024-02-05', '2024-02-22', '2025-02-22', 50),
    ('SP0095', 'Banh gau', 'NCC056', 'Banh Gau', 'BanhKeo', 'Hop',20000,'2024-01-26', '2024-02-01', '2026-02-01', 60),
    ('SP0096', 'Banh hat dieu', 'NCC056', 'Banh quy', 'BanhKeo', 'Goi',20000,'2024-01-26', '2024-02-01', '2026-02-01', 30),
    ('SP0097', 'Banh quy kem socola', 'NCC017', 'Banh quy', 'BanhKeo','Goi', 10000,'2024-02-26', '2024-03-13', '2026-03-13', 50),
    ('SP0098', 'Banh trung Tipo', 'NCC056', 'Banh quy', 'BanhKeo', 'Goi',30000,'2024-01-05', '2024-02-22', '2025-02-22', 60),
    ('SP0099', 'Banh que Oreo', 'NCC017', 'Banh que socola', 'BanhKeo', 'Hop',25000,'2024-01-26', '2024-02-12', '2026-02-12', 20),
    ('SP0100', 'Banh que vi xoai cosy', 'NCC055', 'Banh que vi xoai', 'BanhKeo', 'Goi', 10000,'2024-02-26', '2024-03-28', '2026-03-28',40),
    ('SP0101', 'Banh que vi vani cosy', 'NCC055', 'Banh quy vi vani', 'BanhKeo', 'Goi',10000,'2024-01-05', '2024-02-22', '2025-02-22', 50),
    ('SP0102', 'Banh xop ong nhan kem cosy', 'NCC055', 'Banh xop', 'BanhKeo', 'Goi' , 10000,'2024-02-26', '2024-03-01', '2026-03-01', 60);













    


