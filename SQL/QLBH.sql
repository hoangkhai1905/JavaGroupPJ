USE master
GO
if exists (select * from sysdatabases where name='QLBH')
		drop database QLBH
GO

DECLARE @device_directory nvarchar(520)
SELECT @device_directory = SUBSTRING(filename, 1, CHARINDEX(N'master.mdf', LOWER(filename)) - 1)
FROM master.dbo.sysaltfiles WHERE dbid = 1 AND fileid = 1

EXECUTE (N'CREATE DATABASE QLBH
  ON PRIMARY (NAME = N''QLBH'', FILENAME = N''' + @device_directory + N'QLBH.mdf'')
  LOG ON (NAME = N''QLBH_log'',  FILENAME = N''' + @device_directory + N'QLBH.ldf'')')

GO

USE [QLBH]
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_MaHD]    Script Date: 28/04/2024 12:51:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[AUTO_MaHD]()
RETURNS NVARCHAR(6)
AS
BEGIN
	DECLARE @ID NVARCHAR(6)
	IF (SELECT COUNT(MaHD) FROM HoaDon) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MaHD, 4)) FROM HoaDon
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'HD000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'HD00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_MaKH]    Script Date: 28/04/2024 12:51:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[AUTO_MaKH]()
RETURNS NVARCHAR(6)
AS
BEGIN
	DECLARE @ID NVARCHAR(6)
	IF (SELECT COUNT(MaKH) FROM KhachHang) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MaKH, 3)) FROM KhachHang
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'KH000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'KH00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_MaNCC]    Script Date: 28/04/2024 12:51:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[AUTO_MaNCC]()
RETURNS NVARCHAR(6)
AS
BEGIN
	DECLARE @ID NVARCHAR(6)
	IF (SELECT COUNT(MaNCC) FROM NhaCungCap) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MaNCC, 3)) FROM NhaCungCap
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'NCC00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'NCC0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_MaNV]    Script Date: 28/04/2024 12:51:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[AUTO_MaNV]()
RETURNS NVARCHAR(5)
AS
BEGIN
	DECLARE @ID NVARCHAR(5)
	IF (SELECT COUNT(MaNV) FROM NhanVien) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MaNV, 3)) FROM NhanVien
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'NV00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'NV0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_MaNhomSP]    Script Date: 28/04/2024 12:51:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[AUTO_MaNhomSP]()
RETURNS NVARCHAR(6)
AS
BEGIN
	DECLARE @ID NVARCHAR(6)
	IF (SELECT COUNT(MaNhom) FROM NhomSanPham) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MaNhom, 3)) FROM NhomSanPham
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'NSP00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'NSP0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_MaSP]    Script Date: 28/04/2024 12:51:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[AUTO_MaSP]()
RETURNS NVARCHAR(6)
AS
BEGIN
	DECLARE @ID NVARCHAR(6)
	IF (SELECT COUNT(MaSP) FROM SanPham) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MaSP, 4)) FROM SanPham
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'SP000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'SP00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  Table [dbo].[CT_HoaDon]    Script Date: 28/04/2024 12:51:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_HoaDon](
	[MaHD] [nvarchar](10) NOT NULL,
	[MaSP] [nvarchar](10) NOT NULL,
	[Soluong] [smallint] NULL,
	[ChietKhau] [money] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC,
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 28/04/2024 12:51:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHD] [nvarchar](10) NOT NULL,
	[NgayLapHD] [datetime] NULL,
	[MaKH] [nvarchar](10) NULL,
	[MaNV] [nvarchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 28/04/2024 12:51:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[MaKH] [nvarchar](10) NOT NULL,
	[TenKH] [nvarchar](40) NOT NULL,
	[DiaChi] [nvarchar](60) NULL,
	[SoDT] [nvarchar](15) NULL,
	[DCMail] [nvarchar](50) NULL,
	[DiemTL] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 28/04/2024 12:51:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaCungCap](
	[MaNCC] [nvarchar](10) NOT NULL,
	[TenNCC] [nvarchar](40) NOT NULL,
	[Diachi] [nvarchar](60) NULL,
	[SoDT] [nvarchar](15) NULL,
	[DCMail] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 28/04/2024 12:51:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [nvarchar](10) NOT NULL,
	[Ho] [nvarchar](30) NOT NULL,
	[Ten] [nvarchar](30) NOT NULL,
	[Tuoi] [int] NULL,
	[Phai] [bit] NULL,
	[DiaChi] [nvarchar](50) NULL,
	[SoDT] [nvarchar](15) NULL,
	[TienLuong] [money] NULL,
	[Ca] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhomSanPham]    Script Date: 28/04/2024 12:51:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhomSanPham](
	[MaNhom] [nvarchar](20) NOT NULL,
	[TenNhom] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNhom] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 28/04/2024 12:51:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[MaSP] [nvarchar](10) NOT NULL,
	[TenSP] [nvarchar](40) NOT NULL,
	[MaNCC] [nvarchar](10) NULL,
	[MoTa] [nvarchar](50) NULL,
	[MaNhom] [nvarchar](20) NULL,
	[DonViTinh] [nvarchar](20) NULL,
	[GiaNhap] [money] NULL,
	[NgayNhap] [datetime] NULL,
	[NgaySX] [datetime] NULL,
	[NgayHH] [datetime] NULL,
	[SLTON] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT (getdate()) FOR [NgayLapHD]
GO
ALTER TABLE [dbo].[CT_HoaDon]  WITH CHECK ADD FOREIGN KEY([MaHD])
REFERENCES [dbo].[HoaDon] ([MaHD])
GO
ALTER TABLE [dbo].[CT_HoaDon]  WITH CHECK ADD FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [Makh_FK] FOREIGN KEY([MaKH])
REFERENCES [dbo].[KhachHang] ([MaKH])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [Makh_FK]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [Manv_FK] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [Manv_FK]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD FOREIGN KEY([MaNhom])
REFERENCES [dbo].[NhomSanPham] ([MaNhom])
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [Mancc_FK] FOREIGN KEY([MaNCC])
REFERENCES [dbo].[NhaCungCap] ([MaNCC])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [Mancc_FK]
GO
ALTER TABLE [dbo].[CT_HoaDon]  WITH CHECK ADD CHECK  (([ChietKhau]>=(0)))
GO
ALTER TABLE [dbo].[CT_HoaDon]  WITH CHECK ADD CHECK  (([SoLuong]>(0)))
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD CHECK  (([NgayLapHD]<=getdate()))
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD CHECK  (([NgaySX]<=getdate()))
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD CHECK  (([NgaySX]<=[NgayHH]))
GO
ALTER TABLE [dbo].[Sanpham]  WITH CHECK ADD CHECK  (([NgayHH]>=getdate()))
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD CHECK  (([NgayNhap]<=getdate()))
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD CHECK  (([DiemTL]>=(0)))
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD CHECK  (([SLTON]>=(0)))
GO
CREATE trigger [dbo].[trg_NextMaSP] on [dbo].[SanPham]
for insert
as 
	begin 
		update SanPham set MaSP = dbo.AUTO_MaSP() where MaSP = ''
	end
GO
CREATE trigger [dbo].[trg_NextMaHD] on [dbo].[HoaDon]
for insert
as 
	begin 
		update HoaDon set MaHD = dbo.AUTO_MaHD() where MaHD = ''
	end
GO
CREATE trigger [dbo].[trg_NextMaNV] on [dbo].[NhanVien]
for insert
as 
	begin 
		update NhanVien set MaNV = dbo.AUTO_MaNV() where MaNV = ''
	end
GO
CREATE trigger [dbo].[trg_NextMaNCC] on [dbo].[NhaCungCap]
for insert
as 
	begin 
		update NhaCungCap set MaNCC = dbo.AUTO_MaNCC() where MaNCC = ''
	end
GO
CREATE trigger [dbo].[trg_NextMaKH] on [dbo].[KhachHang]
for insert
as 
	begin 
		update KhachHang set MaKH = dbo.AUTO_MaKH() where MaKH = ''
	end
GO
