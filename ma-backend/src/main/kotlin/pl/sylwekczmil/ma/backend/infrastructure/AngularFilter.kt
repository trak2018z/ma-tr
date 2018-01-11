package pl.sylwekczmil.ma.backend.infrastructure

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.regex.Pattern
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class AngularFilter : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  filterChain: FilterChain) {

        if (isApi(request) || isStatic(request)) {
            filterChain.doFilter(request, response)
        } else {
            request.getRequestDispatcher("/index.html").forward(request, response)
        }
    }

    private fun isApi(request: HttpServletRequest): Boolean {
        return request.requestURI.contains("/api/")
    }

    private fun isStatic(request: HttpServletRequest): Boolean {
        return Pattern.matches("^.*\\.[^\\\\]+$", request.requestURI)
    }
}
