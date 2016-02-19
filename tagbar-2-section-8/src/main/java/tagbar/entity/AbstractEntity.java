package tagbar.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.analysis.cjk.CJKWidthFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.ja.JapaneseBaseFormFilterFactory;
import org.apache.lucene.analysis.ja.JapaneseKatakanaStemFilterFactory;
import org.apache.lucene.analysis.ja.JapaneseTokenizerFactory;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@AnalyzerDef(name = "custom",
		tokenizer =
		@TokenizerDef(factory = JapaneseTokenizerFactory.class, params =
		@org.hibernate.search.annotations.Parameter(name = "userDictionary", value = "userdict.txt")),
		filters = {
				@TokenFilterDef(factory = SynonymFilterFactory.class, params = {
						@org.hibernate.search.annotations.Parameter(name = "synonyms", value = "synonyms.txt"),
						@org.hibernate.search.annotations.Parameter(name = "userDictionary", value = "userdict.txt"),
						@org.hibernate.search.annotations.Parameter(name = "ignoreCase", value = "true"),
						@org.hibernate.search.annotations.Parameter(name = "expand", value = "true"),
						@org.hibernate.search.annotations.Parameter(name = "tokenizerFactory", value = "org.apache.lucene.analysis.ja.JapaneseTokenizerFactory")}),
				@TokenFilterDef(factory = JapaneseBaseFormFilterFactory.class),
				@TokenFilterDef(factory = CJKWidthFilterFactory.class),
				@TokenFilterDef(factory = JapaneseKatakanaStemFilterFactory.class, params = {
						@org.hibernate.search.annotations.Parameter(name = "minimumLength", value = "4")}),
				@TokenFilterDef(factory = LowerCaseFilterFactory.class)
		})
@Getter
@Setter
public abstract class AbstractEntity implements Serializable {
}
