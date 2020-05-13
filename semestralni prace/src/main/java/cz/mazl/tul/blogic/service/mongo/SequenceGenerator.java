package cz.mazl.tul.blogic.service.mongo;

public interface SequenceGenerator {

    /**
     * Serve for generating of sequence for Mongodb entity
     *
     * @param seqName
     * @return
     */
    long generateSequence(String seqName);
}
