class Meja {

}

class Pelanggan {


}

class Makanan {


}

class BahanMakanan {

}

class Pesanan {

}

class Pembayaran {

}

class KantinDSL {
	String nama_kantin
	Datetime jam_buka
	Datetime jam_tutup
	String lokasi

	Pelanggan pelanggan = new Pelanggan()

	def static jual_makanan(pelanggan, closure){
		KantinDSL kdsl = new KantinDSL()
		kdsl.pelanggan = cpty
	    closure.delegate = kdsl
	    closure()
	}

	

}