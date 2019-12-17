package com.xiaoyan.apigateway.Filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulConstants;
import com.xiaoyan.apigateway.exception.RateLimiterException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * 限流---使用Guava RateLimiter限流（令牌桶算法）
 * 令牌桶算法的原理是系统会以一个恒定的速度往桶里放入令牌，而如果请求需要被处理，
 * 则需要先从桶里获取一个令牌，当桶里没有令牌可取时，则拒绝服务。
 */
public class RateLimiterFilter extends ZuulFilter {

    private static final RateLimiter RATE_LIMITER=RateLimiter.create(100);
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        if (!RATE_LIMITER.tryAcquire()){
            throw new RateLimiterException();
        }
        return null;
    }
}
