create database QLBanThucAn03
go
use QLBanThucAn03
go

set dateformat dmy
create table tblDanhMuc (
	idDM	char(7) primary key,
	trangThai	char(1) default 'c'
		check (trangThai in('c', 'x')),
	tenDM	nvarchar(100)
)

insert into tblDanhMuc values
	('DM00001',default, N'Bánh mì'),
	('DM00002',default, N'Xôi'),
	('DM00003',default, N'Bún'),
	('DM00004',default, N'Thức uống')

create table tblSanPham(
	id			char(7) primary key,
	tenSP		nvarchar(100),
	dinhLuong	varchar(20),
	giaBan		money,
	trangThai	char(1) default 'c'
		check (trangThai in('c', 'x')),
	DMNo		char(7) constraint FK_DMNo foreign key references tblDanhMuc(idDM)
		on delete cascade
)

insert into tblSanPham values
	('SP00001', N'Bánh mì chả cá', N'cái', 30000,default, 'DM00001'),
	('SP00002', N'Bánh mì trứng', N'cái', 40000,default, 'DM00001'),
	('SP00003', N'Xôi thịt gà', N'bát', 15000,default, 'DM00002'),
	('SP00004', N'Xôi thập cẩm', N'bát', 20000,default, 'DM00002'),
	('SP00005', N'Bún bò', N'tô', 30000,default, 'DM00003'),
	('SP00006', N'Coca Cola ', N'lon', 10000,default, 'DM00004'),
	('SP00007', N'7 UP ', N'lon', 10000,default, 'DM00004')

create table tblBan (
	idBan		char(7) primary key,
	trangThai	char(1) default 'c'
		check(trangThai in('c', 'x')),
	isOrdered	char(1) default 'k'
		check(isOrdered in ('k', 'r'))
)

insert into tblBan values 
	('M000001', default, default),
	('M000002', default, default),
	('M000003', default, default),
	('M000004', default, default),
	('M000005', default, default),
	('B000001', default, 'r'),
	('B000002', default, default),
	('B000003', default, default),
	('B000004', default, 'r'),
	('B000005', default, default)


create table tblKhachHang (
	maKH				char(7) primary key,
	tenKH				nvarchar(50),
	diachiKH			nvarchar(100),
	SDT					varchar(11) unique
		check(SDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'
		or SDT like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	loaiKH				nvarchar(50)
)

insert into tblKhachHang values
	('KH00001',N'NGUYỄN VĂN A', N'LỘC BÌNH-PHÚ LỘC-TT HUẾ', '0895449987',N'Lẻ'),
	('KH00002',N'NGUYỄN VĂN B', N'48 CAO THẮNG, HẢI CHÂU, ĐÀ NẴNG', '0899243321',N'Lẻ'),
	('KH00003',N'NGUYỄN VĂN C', N'QUẢNG NAM', '0899243351',N'VIP')

create table tblVoucher (
	idVoucher	char(7) primary key,
	tenVoucher	nvarchar(100),
	phanTram	int,
	soLuong		int,
	ngayBatDau	date,
	ngayketThuc	date
)

insert into tblVoucher values 
	('VC00001', N'Khuyến mãi 1', 10, 50, '25/05/2021', '25/06/2021'),
	('VC00002', N'Khuyến mãi 2', 15, 90, '29/04/2021', '01/07/2021')

create table tblDonHang (
	maDH				char(7) primary key,
	ngayTaoDH			date default getdate(),
	thanhToan			char(1) default 'k'
		check(thanhToan in ('k', 'r')),
	maKHNo				char(7) constraint FK_DHHD_KH foreign key references tblKhachHang(maKH)
		on delete set null,
	maBanNo				char(7) constraint FK_DHHD_B foreign key references tblBan(idBan),
	maVCNo				char(7) constraint FK_DHHD_VC foreign key references tblVoucher(idVoucher),
)

insert into tblDonHang values 
	('DH00001', default, default, 'KH00002', 'B000001', 'VC00002'),
	('DH00002', default, default, 'KH00001', 'B000004', null),
	('DH00003', default, 'r', 'KH00003', 'B000002', 'VC00002'),
	('DH00004', default, 'r', 'KH00003', 'B000002', 'VC00002')

create table tblChiTietDonHang (
	idDH	char(7) constraint FK_DH foreign key references tblDonHang(maDH)
		on delete cascade,
	idSP	char(7) constraint FK_SP foreign key references tblSanPham(id)
		on delete cascade,
	SLMua	int,
	giaBan	money,
	primary key(idDH, idSP)
	
)

insert into tblChiTietDonHang values 
	('DH00001', 'SP00001', 4, 30000),
	('DH00001', 'SP00002', 2, 40000),
	('DH00002', 'SP00004', 1, 20000),
	('DH00002', 'SP00003', 5, 15000),
	('DH00003', 'SP00005', 3, 30000),
	('DH00004', 'SP00005', 3, 30000)
	select sum((SLMua)*giaBan) as tongtien
    from tblChiTietDonHang
    where idDH = 'DH00001'
    group by idDH

select * from tblChiTietDonHang ct join tblDonHang d on ct.idDH = d.maDH where d.maBanNo = 'B000004'

go
create function FnDayso0
(
	@max int
)
returns varchar(5)
begin
	declare @dem int = 0, 
	@chuoi varchar(5) = ''
	set @max = @max + 1; 
	while @max <> 0
	begin
		set @dem = @dem + 1;
		set @max = @max/10;
	end
	while (5-@dem) > 0
	begin
		set @chuoi = @chuoi + '0';
		set @dem = @dem + 1;
	end
	return @chuoi
end
Go
create function fnAUTO_ID
( 
	@Ma char(2)
)
returns varchar(7)
begin
	declare @ID varchar(7)
	if @Ma='B0'
		if (select count(idBan) from tblBan) = 0
			set @ID = '0'
		else 
			select @ID = max(right(idBan, 5))  from tblBan
	select @ID = @MA + dbo.fnDayso0(convert(int, @ID)) + convert(char, convert(int, @ID) + 1)
	return @ID
end

go
select * --select các voucher còn hsd
from tblVoucher
where GETDATE() between ngayBatDau and ngayketThuc and soLuong > 0

select sum(SLMua) soLuong --select số lượng từng sản phẩm đã bán
from tblChiTietDonHang as c, tblDonHang as d
where c.idDH = d.maDH and idSP = 'SP00001' and d.thanhToan = 'r'
 group by idSP 

select v.tenVoucher as ten ,v.phanTram as pt,COUNT(maVCNo) as sl, v.idVoucher as id --select số lượng VC đã dùng
from tblDonHang as d, tblVoucher as v
where d.maVCNo = v.idVoucher and d.thanhToan = 'r'
group by d.maVCNo, v.tenVoucher,v.phanTram, v.idVoucher


select sum(phu.thanhTien) as tt  --tổng tiền của các hóa đơn có dùng voucher
from (
		select sum(SLMua*giaBan) as thanhTien , maVCNo --tien mỗi hóa đơn
		from tblChiTietDonHang as c, tblDonHang as d
		where c.idDH=d.maDH and maVCNo = 'VC00002' and d.thanhToan = 'r'
		group by idDH, maVCNo ) as phu
group by phu.maVCNo

select * --lấy sản phẩm trong khoangr thời gian
from tblSanPham as s
where s.id in (
	select idSP
	from tblChiTietDonHang as c , tblDonHang as d
	where c.idDH= d.maDH and d.ngayTaoDH between convert(date,'25/05/2021',103) and convert(date,'25/06/2021',103) and d.thanhToan ='r' )

select sum(SLMua) soLuong --select số lượng từng sản phẩm đã bán trong khoảng thời gian
from tblChiTietDonHang as c , tblDonHang as d
where c.idDH=d.maDH and idSP = 'SP00005' and d.ngayTaoDH between convert(date,'25/05/2021',103) 
and convert(date,'25/06/2021',103) and d.thanhToan ='r' 
group by idSP 

select v.tenVoucher as ten ,v.phanTram as pt,COUNT(maVCNo) as sl, v.idVoucher as id --select số lượng VC đã dùng trong khoảng thời gian
from tblDonHang as d, tblVoucher as v
where d.maVCNo = v.idVoucher and d.ngayTaoDH between convert(date,'25/05/2021',103) 
and convert(date,'25/06/2021',103) and d.thanhToan ='r' 
group by d.maVCNo, v.tenVoucher,v.phanTram, v.idVoucher

select sum(phu.thanhTien) as tt  --tổng tiền của các hóa đơn có dùng voucher trong khoảng thời gian
from (
		select sum(SLMua*giaBan) as thanhTien , maVCNo --tien mỗi hóa đơn
		from tblChiTietDonHang as c, tblDonHang as d
		where c.idDH=d.maDH and maVCNo = 'VC00002' and d.ngayTaoDH between convert(date,'25/05/2021',103) 
and convert(date,'25/06/2021',103) and d.thanhToan ='r' 
		group by idDH, maVCNo ) as phu
group by phu.maVCNo

select sum(phu.thanhTien) as tt  --tổng tiền của các hóa đơn có dùng voucher
from (
		select sum(SLMua*giaBan) as thanhTien , maVCNo --tien mỗi hóa đơn
		from tblChiTietDonHang as c, tblDonHang as d
		where c.idDH=d.maDH and maVCNo = 'VC00002' and d.thanhToan = 'r' and d.ngayTaoDH between convert(date,'25/05/2021',103) 
and convert(date,'25/06/2021',103)
		group by idDH, maVCNo ) as phu
group by phu.maVCNo

select * --sản phẩm bán chạy nhất
from tblSanPham
where id in(
select idSP
from tblChiTietDonHang as c , tblDonHang as d
where c.idDH = d.maDH and d.thanhToan = 'r'
group by idSP
 having sum(SLMua) = ( select top 1 sum(SLMua) soLuong  
										 from tblChiTietDonHang 
										 group by idSP 
										 order by soLuong desc))

select *
from tblSanPham 
where id in (
select idSP 
from tblChiTietDonHang as c, tblDonHang as d
where c.idDH = d.maDH and d.thanhToan = 'r' 
group by idSP 
having sum(SLMua) = ( select top 1 sum(SLMua) soLuong  from tblChiTietDonHang as c, tblDanhMuc as d, tblSanPham as s
where c.idSP = s.id and s.DMNo = d.idDM and d.idDM = 'DM00003'
group by idSP order by soLuong desc)
)

select *
from tblSanPham 
where id in (
select idSP 
from tblChiTietDonHang as c, tblDonHang as h
where c.idDH=h.maDH  and h.thanhToan = 'r' and convert(date,ngayTaoDH,103) between convert(date,'25/5/2021',103) and CONVERT(date,'25/6/2021',103)
group by idSP 
having sum(SLMua) = ( select top 1 sum(SLMua) soLuong  from tblChiTietDonHang as c, tblDonHang as h, tblDanhMuc as d, tblSanPham as s
where c.idDH=h.maDH and c.idSP = s.id and s.DMNo = d.idDM and d.idDM = 'DM00003' and 
h.thanhToan = 'r' and convert(date,ngayTaoDH,103) between convert(date,'13/5/2021',103) and CONVERT(date,'29/5/2021',103)
 group by idSP order by soLuong desc )
 )

 select * 
 from tblSanPham 
 where id NOT IN(
 select idSP 
 from tblChiTietDonHang as c, tblDonHang as h
 where c.idDH=h.maDH and h.thanhToan = 'r'
 ) and DMNo = 'DM00002'

 select * 
 from tblSanPham 
 where id NOT IN(
 select idSP 
 from tblChiTietDonHang as c, tblDonHang as h 
 where c.idDH=h.maDH and h.thanhToan = 'r' and convert(date,ngayTaoDH,103) between convert(date,'13/5/2021',103) and CONVERT(date,'29/5/2021',103)
 ) and DMNo = 'DM00002'

 select max(right(maKH,5)) from tblKhachHang