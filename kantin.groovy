

class KantinDSLTest extends GroovyTestCase {

    void test() {
        KantinDSL.buat_transaksi {
            beli "ayam goreng"
            jumlah 1
            status "dine in"
            uang 11000
            process
            beli "ayam goreng"
            jumlah 1
            status "dine in"
            uang 11000
            process
            beli "ayam goreng"
            jumlah 1
            status "dine in"
            uang 11000
            process
            pergi "dine in"
            beli "ayam goreng"
            jumlah 1
            status "take away"
            uang 11000
            process
            pergi "take away"
            beli "ayam goreng"
            jumlah 1
            status "take away"
            uang 11000
            process
            pergi "dine in"
            pergi "dine in"	
            pergi "take away"
        }
    }
}



class KantinDSL {
	String nama_kantin
	int jam_buka
	int jam_tutup
	String lokasi
	int status_pembelian = 0
    int idx = -1
    def menu = ["ayam goreng", 'tahu', 'bebek']
    int quantity = -1
    def stock = [3,3,1]
    int meja = 0
    int maksMeja = 2
    int uang = 5000
    def harga = [10000,2000,12000]
    int kembalian = -1
    def sections = []


	def static buat_transaksi(closure){
		KantinDSL kdsl = new KantinDSL()
	    closure.delegate = kdsl
	    closure()
	}

	def beli(String makanan) {
        this.status_pembelian = 0
        this.idx = this.menu.indexOf(makanan)
        if (this.idx == null) {
            this.status_pembelian = 1
        }
    }

    def jumlah(int quantity) {
        this.quantity = quantity
        if (this.status_pembelian == 0) {
            if ((this.stock[this.idx]-quantity) < 0) {
                this.status_pembelian = 2
            } else {
                this.stock[this.idx] -= quantity
            }
        }
    }

    def status(String wrapper) {
        if (this.status_pembelian == 0) {
            if (wrapper == 'dine in') {
                if (meja == maksMeja) {
                    this.status_pembelian = 3
                } else {
                    this.meja += 1
                }
            }
        }
    }

    def uang(int money) {
        if (this.status_pembelian == 0) {
            if (money < (this.harga[this.idx]*this.quantity)) {
                this.status_pembelian = 4
            } else if (money == (this.harga[this.idx]*this.quantity)) {
                this.uang += money
            } else {
                this.uang += money
                this.kembalian = money - (this.harga[this.idx]*this.quantity)
            }
        }
    }

    def pergi(String wrapper){
    	if (wrapper == 'dine in') {
			if (this.meja > 0){
	    		this.meja -= 1
	    		println "Pelanggan yang melakukan dine in pergi\n"
	    	}
	    	else {
	    		println "Command error. Jumlah meja = 0, operasi pergi invalid\n"
	    	}
    	}
    	else {
    		println "Pelanggan yang melakukan take away pergi\n"
    	}
    }

    def getProcess() {
        doProcess(this)
    }

    private static doProcess(KantinDSL transaksi) {
        if (transaksi.status_pembelian == 0) {
            println "Pembelian Sukses"
            println "Kembalian ${transaksi.kembalian}\n"
        } else if (transaksi.status_pembelian == 1) {
            println "Pembelian Gagal"
            println "Menu Tidak Ditemukan\n"
        } else if (transaksi.status_pembelian == 2) {
            println "Pembelian Gagal"
            println "Jumlah Stok Kurang\n"
        } else if (transaksi.status_pembelian == 3) {
            println "Pembelian Gagal"
            println "Meja Penuh\n"
        } else {
            println "Pembelian Gagal"
            println "Uang Anda Kurang\n"
        }
    }


}

class Transaksi {

}

class Meja {

}

class Pelanggan {


}

class Makanan {


}

class BahanMakanan {

}
