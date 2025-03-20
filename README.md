# Audion - Offline Audio Fingerprinting & Recognition

## Overview
**Audion** is a Java-based graphical user interface (GUI) application for offline audio fingerprinting and recognition. Inspired by **Shazam**, Audion is a fun, recreational project that allows users to identify songs without an Internet connection. Unlike cloud-based solutions, Audion works entirely offline, using a custom Fourier Transform implementation to extract unique audio features for identification.

## Features
- 🎵 **Offline Audio Recognition** – Match audio clips without an Internet connection.
- 🎛 **Custom Fourier Transform** – No third-party FFT libraries; fully custom implementation.
- 🎚 **Spectrogram Generation** – Visualize audio frequencies over time.
- 🎤 **Waveform Display** – Interactive GUI with waveform visualization.
- 📂 **Local Database (SQLite/H2)** – Store and retrieve audio fingerprints efficiently.
- 🖥 **JavaFX-Based UI** – User-friendly graphical interface.

## Tech Stack
- **Language:** Java (JDK 17+ recommended)
- **GUI:** JavaFX
- **Audio Processing:** Custom Fourier Transform (DFT/FFT), Java Sound API
- **Storage:** SQLite or H2 (Embedded Database)
- **Visualization:** JavaFX Canvas for waveform and spectrogram rendering

## Installation
### Prerequisites
- Java Development Kit (JDK 17+)
- JavaFX (if not bundled with JDK)

### Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/lvntky/audion.git
   cd audion
   ```
2. Compile and run:
   ```sh
   javac -d bin src/**/*.java
   java -cp bin com.leventkaya.Main
   ```

## How It Works
1. **Load an Audio File** – Drag and drop a WAV/MP3 file into Audion.
2. **Feature Extraction** – The custom Fourier Transform processes the audio signal.
3. **Fingerprinting** – Unique spectral peaks are stored in the local database.
4. **Recognition** – A new clip is compared against stored fingerprints to identify matches.
5. **Visualization** – Spectrogram and waveform displays help analyze the audio.

## Future Enhancements
- 🎯 **Real-time Audio Matching** – Support live microphone input.
- 🔄 **Noise Reduction** – Improve recognition under noisy conditions.
- 🏷 **Metadata Extraction** – Retrieve song details from local storage.

## License
This project is licensed under the MIT License.

## Contributing
Contributions are welcome! Feel free to open an issue or submit a pull request.

## Acknowledgements
- [Creating Shazam in Java](https://www.royvanrijn.com/blog/2010/06/creating-shazam-in-java/)
- [How Shazam Works](http://web.archive.org/web/20100813022319/http://laplacian.wordpress.com/2009/01/10/how-shazam-works/)
- [An Industrial-Strength Audio Search Algorithm](https://web.archive.org/web/20100622213403/http://www.ee.columbia.edu/~dpwe/papers/Wang03-shazam.pdf)
## Contact
📧 leventkayadev@gmail.com

