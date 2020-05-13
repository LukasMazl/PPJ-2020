package cz.mazl.tul.dto.out.index;

import java.util.List;
import java.util.Map;

public class IndexDataDTO {
    private Map<String, String> countryCodes;
    private String codeSelectedCountry;
    private List<IndexCountryDataDTO> indexCountryDataDTOList;

    public Map<String, String> getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(Map<String, String> countryCodes) {
        this.countryCodes = countryCodes;
    }

    public String getCodeSelectedCountry() {
        return codeSelectedCountry;
    }

    public void setCodeSelectedCountry(String codeSelectedCountry) {
        this.codeSelectedCountry = codeSelectedCountry;
    }

    public List<IndexCountryDataDTO> getIndexCountryDataDTOList() {
        return indexCountryDataDTOList;
    }

    public void setIndexCountryDataDTOList(List<IndexCountryDataDTO> indexCountryDataDTOList) {
        this.indexCountryDataDTOList = indexCountryDataDTOList;
    }

    @Override
    public String toString() {
        return "IndexDataDTO{" +
                "countryCodes=" + countryCodes +
                ", codeSelectedCountry='" + codeSelectedCountry + '\'' +
                ", indexCountryDataDTOList=" + indexCountryDataDTOList +
                '}';
    }
}
