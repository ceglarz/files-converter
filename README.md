# files-converter

Aplikacja czyta pliki tekstowe (znajdujące sie w katalogu "files-converter\data") o różnych strukturach (XML lub CSV), transformuje je i zapisuje do bazy danych o ustalonej strukturze.

### Konwertery
Ze względu na duże rozmiary plików, załadowanie ich do pamięci może być kosztowne. W tym celu zastosowano:
  - strumieniowe przesyłanie pliku
  - parser SAX

### Architektura
Konwertery są produkowane przy pomocy wzorca prostej fabryki (simple factory) w zależności od rozszerzenia pliku.
Operacje możliwe do wykonania z przygotowanym obiektem "Customer" są zawarte w klasie "CustomerService". Takie podejście pozwala na implementację tych metod (np. zapis do bazy) tylko w jednym miejscu aplikacji.

### Konfiguracja
Aplikacja została zbudowana przy pomocy narzędzia Maven, natomiast konfiguracja dostępu do bazy danych została umieszczona w pliku "resources\application.properties".

### Typ kontaktu

Możliwe typu kontaktu (wraz z wartością) przedstawia tabela:

| Wartość | Typ |
| ------ | ------ |
| 0 | unknown |
| 1 | email |
| 2 | phone |
| 3 | jabber |

W przypadku plików XML informacja o typie kontaktu jest zawarta w nazwie węzła. 
Pliki CSV nie posiadają takiej informacji. W tym przypadku przygotowano specjalną metodę walidującą dane. Przygotowano również testy, które sprawdzają poprawność walidacji.

### Baza danych
Plik generujący tabele bazy danych został umieszczony w pliku "files-converter-generate.sql". Jak napisano wcześniej, konfiguracja dostępu do bazy danych została umieszczona w pliku "resources\application.properties".