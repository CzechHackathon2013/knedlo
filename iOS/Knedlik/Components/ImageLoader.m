//
//  ImageLoader.m
//  Knedlo
//
//  Created by Martin Wenisch on 10/20/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import "ImageLoader.h"

@implementation ImageLoader

+ (void)loadImageWithURL:(NSURL *)url completionHandler:(void (^)(UIImage *img))handler
{
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        NSData *data = [NSData dataWithContentsOfURL:url];
        
        UIImage *image = [UIImage imageWithData:data];
        dispatch_async(dispatch_get_main_queue(), ^{
            handler(image);
        });
    });
}

@end
