{stdenv, jdk, maven, ant} : 
stdenv.mkDerivation {
    name = "gridgaincache";
    src = ./gridgaincache;
    buildInputs = [jdk ant maven];
    buildPhase = "mvn compile";
}