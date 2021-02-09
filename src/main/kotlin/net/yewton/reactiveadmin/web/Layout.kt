package net.yewton.reactiveadmin.web

import com.samskivert.mustache.Mustache
import com.samskivert.mustache.Mustache.Compiler
import com.samskivert.mustache.Template
import org.springframework.stereotype.Component
import java.io.Writer

@Component
class Layout(private val compiler: Compiler): Mustache.Lambda {

    lateinit var body: String

    override fun execute(fragment: Template.Fragment, out: Writer) {
        this.body = fragment.execute()
        compiler.compile("{{>layout}}").execute(fragment.context(), out)
    }
}
