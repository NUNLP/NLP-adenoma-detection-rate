import static org.northshore.cbri.UIMAUtil.*

import java.util.regex.Matcher

import edu.northwestern.fsm.type.ColonoscopyProcedure

ColonoscopyProcedure proc = select(type:ColonoscopyProcedure).find()
Matcher matcher = jcas.documentText =~ /(?i)(?m)^_+$\s+^(([A-Z][^ ]+) ([A-Z][^ ]*? )*?([A-Z][^ ]+)), M\.?D\.?$/
matcher.each {
    proc.provider = matcher.group(1)
}

if (proc.provider == null) {
    matcher = jcas.documentText =~ /Dictated by:\s*(.+),\s*MD/
    matcher.each {
        proc.provider = matcher.group(1)
    }
}
