package com.afifa.praktikum2_123140073

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// Struktur data untuk berita.
data class Berita(val id: Int, val judul: String, val kategori: String)

// StateFlow untuk menghitung total berita yang sudah dibaca
val totalDibaca = MutableStateFlow(0)

fun main() = runBlocking {
    println("=== Menjalankan News Feed Simulator ===")

    // Panggil sumber berita
    sumberBeritaFlow()
        // 2. Filter yang hanya memproses berita "Teknologi"
        .filter { berita ->
            berita.kategori == "Teknologi"
        }
        // 3. Map ubah bentuk datanya biar rapi saat dicetak
        .map { berita ->
            "[${berita.kategori}] ${berita.judul}"
        }
        // Operator onEach: Cetak pemberitahuan kalau ada berita yang lewat
        .onEach { teksBerita ->
            println("\n-> Ada berita teknologi baru: $teksBerita")
        }
        // 1. Collect: Mulai mengumpulkan data dari atas ke bawah
        .collect { teksBerita ->

            // 5. Coroutines async/await & Dispatcher: mengunduh isi berita
            val prosesDownload = async(Dispatchers.IO) {
                ambilIsiBerita(teksBerita)
            }

            // Tunggu (await) sampai proses downloadnya selesai
            val hasilDownload = prosesDownload.await()
            println(hasilDownload)

            // Update angka di StateFlow
            totalDibaca.value += 1
            println("Total berita teknologi yang sudah dibaca: ${totalDibaca.value}")
        }

    println("\n=== Semua berita hari ini sudah selesai diproses. ===")
}

// 1. Flow Builder: Mengeluarkan (emit) berita baru setiap 2 detik
fun sumberBeritaFlow(): Flow<Berita> = flow {
    val pilihanKategori = listOf("Teknologi", "Olahraga", "Politik", "Hiburan")

    // Simulasi hanya ada 10 berita hari ini agar program bisa berhenti dengan sendirinya
    for (i in 1..10) {
        delay(2000) // Wajib: Jeda 2 detik sesuai ketentuan tugas

        val beritaBaru = Berita(
            id = i,
            judul = "Berita Ke-$i",
            kategori = pilihanKategori.random() // Kategori diacak otomatis
        )

        emit(beritaBaru) // Lempar beritanya ke Flow
    }
}

// Fungsi bantuan buat simulasi menunggu loading jaringan
suspend fun ambilIsiBerita(judul: String): String {
    delay(1000) // Anggap butuh 1 detik buat loading data dari internet
    return "Isi lengkap dari '$judul' berhasil ditampilkan."
}